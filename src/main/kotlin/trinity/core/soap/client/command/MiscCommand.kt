package trinity.core.soap.client.command

class MiscCommand private constructor() {
    companion object {
        fun notify(message: String): Command {
            return Command("notify $message");
        }
    }
}