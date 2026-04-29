package net.twinte.android

import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.core.content.edit

private const val ENABLE_SCHEDULE_NOTIFICATION_KEY = "enable_schedule_notification"
private const val NOTIFICATION_TIMING_KEY = "notification_timing"

@Composable
fun SettingsScreen(
    title: String,
    sharedPreferences: SharedPreferences,
    versionName: String,
    scheduleOptionLabels: List<Pair<String, String>>,
    defaultScheduleTiming: List<String>,
    onBack: () -> Unit,
    onScheduleNotificationChanged: () -> Unit,
    onOpenLicense: () -> Unit,
    onOpenTwitter: () -> Unit,
    onOpenMail: () -> Unit,
    onOpenWebsite: () -> Unit,
    onOpenGithub: () -> Unit,
) {
    val context = androidx.compose.ui.platform.LocalContext.current
    val scope = rememberCoroutineScope()
    val labelMap = remember(scheduleOptionLabels) { scheduleOptionLabels.toMap() }

    var notificationsEnabled by remember {
        mutableStateOf(sharedPreferences.getBoolean(ENABLE_SCHEDULE_NOTIFICATION_KEY, true))
    }
    var notificationTimings by remember {
        mutableStateOf(sharedPreferences.notificationTiming(defaultScheduleTiming))
    }
    var showTimingDialog by remember { mutableStateOf(false) }
    var githubVisible by remember { mutableStateOf(false) }
    var versionTapTime by remember { mutableLongStateOf(0L) }
    var versionTapCount by remember { mutableIntStateOf(0) }

    DisposableEffect(sharedPreferences, defaultScheduleTiming) {
        val listener = SharedPreferences.OnSharedPreferenceChangeListener { preferences, key ->
            when (key) {
                ENABLE_SCHEDULE_NOTIFICATION_KEY -> {
                    notificationsEnabled = preferences.getBoolean(ENABLE_SCHEDULE_NOTIFICATION_KEY, true)
                }
                NOTIFICATION_TIMING_KEY -> {
                    notificationTimings = preferences.notificationTiming(defaultScheduleTiming)
                }
            }
        }
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
        onDispose {
            sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)
        }
    }

    if (showTimingDialog) {
        var draftTimings by remember(showTimingDialog) {
            mutableStateOf(notificationTimings.toSet())
        }
        AlertDialog(
            onDismissRequest = { },
            title = {
                Text(text = stringResource(R.string.settings_notification_timing), fontWeight = FontWeight.SemiBold)
            },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState()),
                ) {
                    scheduleOptionLabels.forEach { (key, label) ->
                        val isChecked = draftTimings.contains(key)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    draftTimings = draftTimings.toggle(key)
                                }
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Checkbox(
                                checked = isChecked,
                                onCheckedChange = {
                                    draftTimings = draftTimings.toggle(key)
                                },
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = label)
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        val sortedTimings = draftTimings.toList().sortedBy(String::toInt)
                        notificationTimings = sortedTimings
                        sharedPreferences
                            .edit {
                                putStringSet(NOTIFICATION_TIMING_KEY, sortedTimings.toSet())
                            }
                        onScheduleNotificationChanged()
                    },
                ) {
                    Text(stringResource(R.string.settings_save))
                }
            },
            dismissButton = {
                TextButton(onClick = { }) {
                    Text(stringResource(R.string.settings_cancel))
                }
            },
        )
    }

    TwinteSettingsTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    modifier = Modifier.statusBarsPadding(),
                    title = {
                        Text(
                            text = title,
                            fontWeight = FontWeight.Bold,
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.settings_back),
                            )
                        }
                    },
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = MaterialTheme.colors.onPrimary,
                    elevation = 6.dp,
                )
            },
            backgroundColor = MaterialTheme.colors.background,
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 20.dp)
                    .navigationBarsPadding(),
                verticalArrangement = Arrangement.spacedBy(18.dp),
            ) {
                SettingsSection(title = stringResource(R.string.settings_section_schedule)) {
                    SwitchSettingsRow(
                        iconResId = R.drawable.ic_event,
                        title = stringResource(R.string.settings_schedule_notification_title),
                        summary = stringResource(R.string.settings_schedule_notification_summary),
                        checked = notificationsEnabled,
                        onCheckedChange = { checked ->
                            notificationsEnabled = checked
                            sharedPreferences
                                .edit {
                                    putBoolean(ENABLE_SCHEDULE_NOTIFICATION_KEY, checked)
                                }
                            onScheduleNotificationChanged()
                        },
                    )
                    SettingsDivider()
                    ActionSettingsRow(
                        iconResId = R.drawable.ic_alarm,
                        title = stringResource(R.string.settings_notification_timing),
                        summary = notificationTimings.toSummary(
                            labelMap = labelMap,
                            emptyLabel = stringResource(R.string.settings_unselected),
                            separator = stringResource(R.string.settings_summary_separator),
                        ),
                        enabled = notificationsEnabled,
                        onClick = { },
                    )
                }

                SettingsSection(title = stringResource(R.string.settings_section_feedback)) {
                    ActionSettingsRow(
                        iconResId = R.drawable.ic_twitter,
                        title = stringResource(R.string.settings_twitter_title),
                        summary = stringResource(R.string.settings_twitter_summary),
                        onClick = onOpenTwitter,
                    )
                    SettingsDivider()
                    ActionSettingsRow(
                        iconResId = R.drawable.ic_mail_outline,
                        title = stringResource(R.string.settings_mail_title),
                        summary = stringResource(R.string.settings_mail_address),
                        onClick = onOpenMail,
                    )
                }

                SettingsSection(title = stringResource(R.string.settings_section_info)) {
                    ActionSettingsRow(
                        iconResId = R.drawable.ic_source,
                        title = stringResource(R.string.settings_license_title),
                        summary = "",
                        onClick = onOpenLicense,
                    )
                    SettingsDivider()
                    ActionSettingsRow(
                        iconResId = R.drawable.ic_web,
                        title = stringResource(R.string.settings_site_title),
                        summary = stringResource(R.string.settings_site_url),
                        onClick = onOpenWebsite,
                    )
                    if (githubVisible) {
                        SettingsDivider()
                        ActionSettingsRow(
                            iconResId = R.drawable.ic_github,
                            title = stringResource(R.string.settings_github_title),
                            summary = stringResource(R.string.settings_github_url),
                            onClick = onOpenGithub,
                        )
                    }
                    SettingsDivider()
                    ActionSettingsRow(
                        iconResId = R.drawable.ic_icon_small,
                        title = stringResource(R.string.settings_app_version_title),
                        summary = stringResource(R.string.settings_app_version_format, versionName),
                        onClick = {
                            val currentTime = System.currentTimeMillis()
                            if (currentTime - versionTapTime < 500) {
                                versionTapCount += 1
                                if (versionTapCount >= 7) {
                                    Toast.makeText(
                                        context,
                                        context.getString(R.string.settings_version_easter_egg_done),
                                        Toast.LENGTH_LONG,
                                    ).show()
                                    if (!githubVisible) {
                                        scope.launch {
                                            delay(1000)
                                            githubVisible = true
                                        }
                                    }
                                } else if (versionTapCount >= 4) {
                                    Toast.makeText(
                                        context,
                                        context.getString(
                                            R.string.settings_version_easter_egg_progress,
                                            7 - versionTapCount,
                                        ),
                                        Toast.LENGTH_SHORT,
                                    ).show()
                                }
                            }
                            versionTapTime = currentTime
                        },
                    )
                }
            }
        }
    }
}

@Composable
private fun TwinteSettingsTheme(content: @Composable () -> Unit) {
    val primary = colorResource(R.color.colorPrimary)
    val secondary = colorResource(R.color.colorAccent)
    val background = colorResource(R.color.background)
    val onSurface = colorResource(R.color.widget_text_main)

    val colors = if (isSystemInDarkTheme()) {
        darkColors(
            primary = primary,
            secondary = secondary,
            background = background,
            surface = Color(0xFF252C39),
            onPrimary = Color.White,
            onSecondary = Color.White,
            onBackground = onSurface,
            onSurface = onSurface,
        )
    } else {
        lightColors(
            primary = primary,
            secondary = secondary,
            background = background,
            surface = Color.White,
            onPrimary = Color.White,
            onSecondary = Color.White,
            onBackground = onSurface,
            onSurface = onSurface,
        )
    }

    MaterialTheme(colors = colors, content = content)
}

@Composable
private fun SettingsSection(
    title: String,
    content: @Composable () -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.primary,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 6.dp),
        )
        Card(
            backgroundColor = MaterialTheme.colors.surface,
            shape = RoundedCornerShape(24.dp),
            elevation = 4.dp,
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                content()
            }
        }
    }
}

@Composable
private fun SwitchSettingsRow(
    iconResId: Int,
    title: String,
    summary: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    SettingsRow(
        iconResId = iconResId,
        title = title,
        summary = summary,
        onClick = { onCheckedChange(!checked) },
        trailing = {
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange,
            )
        },
    )
}

@Composable
private fun ActionSettingsRow(
    iconResId: Int,
    title: String,
    summary: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    SettingsRow(
        iconResId = iconResId,
        title = title,
        summary = summary,
        enabled = enabled,
        onClick = onClick,
    )
}

@Composable
private fun SettingsRow(
    iconResId: Int,
    title: String,
    summary: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
    trailing: @Composable (RowScope.() -> Unit)? = null,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .alpha(if (enabled) 1f else 0.45f)
            .clickable(enabled = enabled, onClick = onClick)
            .padding(horizontal = 18.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconBadge(iconResId = iconResId)
        Spacer(modifier = Modifier.width(14.dp))
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.SemiBold,
            )
            if (summary.isNotEmpty()) {
                Text(
                    text = summary,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.74f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
        if (trailing != null) {
            Spacer(modifier = Modifier.width(12.dp))
            trailing()
        }
    }
}

@Composable
private fun IconBadge(iconResId: Int) {
    Box(
        modifier = Modifier
            .size(42.dp)
            .background(
                color = MaterialTheme.colors.primary.copy(alpha = 0.14f),
                shape = CircleShape,
            ),
        contentAlignment = Alignment.Center,
    ) {
        androidx.compose.material.Icon(
            painter = painterResource(iconResId),
            contentDescription = null,
            tint = MaterialTheme.colors.primary,
        )
    }
}

@Composable
private fun SettingsDivider() {
    Divider(
        modifier = Modifier.padding(horizontal = 18.dp),
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f),
    )
}

private fun SharedPreferences.notificationTiming(defaultTiming: List<String>): List<String> =
    getStringSet(NOTIFICATION_TIMING_KEY, defaultTiming.toSet())
        ?.toList()
        ?.sortedBy(String::toInt)
        ?: defaultTiming

private fun Set<String>.toggle(value: String): Set<String> =
    if (contains(value)) {
        this - value
    } else {
        this + value
    }

private fun List<String>.toSummary(
    labelMap: Map<String, String>,
    emptyLabel: String,
    separator: String,
): String =
    mapNotNull(labelMap::get)
        .ifEmpty { listOf(emptyLabel) }
        .joinToString(separator)
