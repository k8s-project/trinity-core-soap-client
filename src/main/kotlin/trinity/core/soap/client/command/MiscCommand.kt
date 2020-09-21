package trinity.core.soap.client.command

import trinity.core.soap.client.command.Command

class MiscCommand private constructor() {
    companion object {
        fun notify(message: String): Command {
            return Command("notify $message");
        }
    }
}