package trinity.core.soap.client.command

class AccountCommand {
    companion object {
        fun create(username: String, password: String): Command {
            return Command("create $username $password")
        }

        fun delete(username: String): Command {
            return Command("delete $username")
        }

        fun lock(username: String): Command {
            return Command("lock $username")
        }

        fun changePassword(username: String, password: String): Command {
            return Command("set password $username $password $password")
        }

        fun gmLevel(username: String, gmLevel: Number, realmId: Number = -1): Command {
            return Command("set gmlevel $username $gmLevel $realmId")
        }
    }
}
