package net.twinte.android.widget

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import kotlinx.coroutines.*
import net.twinte.android.HttpError
import net.twinte.android.Network
import net.twinte.android.types.Period
import net.twinte.android.R
import net.twinte.android.types.Day
import net.twinte.android.types.Module
import java.lang.Exception

/**
 * 時間割ListViewのアダプタ
 */
class WidgetListViewService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory {
        return ListViewRemoteFactory(applicationContext, intent)
    }

    class ListViewRemoteFactory(val mContext: Context, val intent: Intent) : RemoteViewsFactory {

        data class PeriodTerm(val start: String, val end: String)

        val periodTerm = arrayOf(
            PeriodTerm("08:40", "09:55"),
            PeriodTerm("10:10", "11:25"),
            PeriodTerm("12:15", "13:30"),
            PeriodTerm("13:45", "15:00"),
            PeriodTerm("15:15", "16:30"),
            PeriodTerm("16:45", "18:00")
        )

        var periods: Array<Period> = emptyArray()
        var lastUpdate = ""

        val mAppWidgetId = intent.getIntExtra(
            AppWidgetManager.EXTRA_APPWIDGET_ID,
            AppWidgetManager.INVALID_APPWIDGET_ID
        )

        private suspend fun updateTimetable() = try {
            Log.d("WIDGET", "updateTimetable")
            periods = Network.fetchTimetable(TimetableWidget.date)
            lastUpdate = TimetableWidget.date
        } catch (e: HttpError) {
            periods = arrayOf(
                Period("", e.message ?: "HttpError", "", 0, Module.Unknown, Day.Sun, 1, "", "")
            )
        } catch (e: Exception) {
            periods = arrayOf(
                Period("", e.message ?: "Unknown Error", "", 0, Module.Unknown, Day.Sun, 1, "", "")
            )
        }

        override fun onCreate() {}

        override fun getLoadingView() = null

        override fun getItemId(position: Int) = position.toLong()

        override fun onDataSetChanged() {
            Log.d("WIDGET", "onDataSetChanged")
            runBlocking {
                updateTimetable()
            }
        }

        override fun hasStableIds() = true

        override fun getViewAt(position: Int): RemoteViews {
            if (TimetableWidget.date != lastUpdate)
                runBlocking {
                    updateTimetable()
                }

            val p = periods.find { it.period == position + 1 }
            val rv = RemoteViews(
                mContext.packageName,
                if (p != null) R.layout.item_period else R.layout.item_period_disabled
            )

            rv.run {
                setTextViewText(R.id.period_number_text_view, (position + 1).toString())
                setTextViewText(R.id.period_start_text_view, periodTerm[position].start)
                setTextViewText(R.id.period_end_text_view, periodTerm[position].end)
                setTextViewText(R.id.period_name_text_view, p?.lecture_name ?: "")
                setTextViewText(R.id.period_instructor_text_view, p?.instructor ?: "")
                setTextViewText(R.id.period_room_text_view, p?.room ?: "")
                if (p != null)
                    setOnClickFillInIntent(R.id.period_wrapper, Intent().apply {
                        // タップした講義を判定するためにuserLectureIdを付与
                        putExtra("user_lecture_id", p.user_lecture_id)
                    })
            }

            return rv
        }

        override fun getCount() = periodTerm.size

        override fun getViewTypeCount() = 2

        override fun onDestroy() {}

    }

}
