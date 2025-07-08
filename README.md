# twinte-android

Twin:teのAndroid版ネイティブアプリ

## code generation

1. https://buf.build/docs/cli/installation/ を参考にBuf CLIをインストールしてください
2. https://github.com/twin-te/twin-te をクローンします（ここでは `/path/to/twin-te` にクローンしたものとする）
3. リポジトリのルートで以下のコマンドを実行するとAPI用のコードが生成されます

```shell
buf generate --template ./buf.gen.yml </path/to/twin-te/proto>
```
