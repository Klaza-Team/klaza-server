package klaza.klaza_server.data

class EventOtherData(
    var modulename: String,
    var instanceid: Long,
    var name: String) {

    override fun toString(): String {
        return "EventOtherData(modulename='$modulename', instanceid=$instanceid, name='$name')"
    }

}