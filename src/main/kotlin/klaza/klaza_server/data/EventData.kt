package klaza.klaza_server.data

import klaza.klaza_server.dtos.EventDTO
import klaza.klaza_server.models.Course
import klaza.klaza_server.models.User

class EventData(
    var eventname: String,
    var objectid: String,
    var crud: String,
    var contextlevel: Long,
    var contextid: Long,
    var user: User?,
    var course: Course?,
    var relateduser: User?,
    var action: String,
    var target: String,
    var other: EventOtherData) {

    fun convertToDTO(): EventDTO {
        return EventDTO(
            eventname = eventname,
            objectid = objectid,
            crud = crud,
            contextlevel = contextlevel,
            contextid = contextid,
            userid = if (user != null) user!!.getId()!! else 0,
            courseid = if (course != null) course!!.getId()!! else 0,
            relateduserid = if (relateduser != null) relateduser!!.getId()!! else 0,
            action = action,
            target = target,
            other = other
        )
    }

    override fun toString(): String {
        return "EventData(eventname='$eventname', objectid='$objectid', crud='$crud', contextlevel=$contextlevel, contextid=$contextid, user=$user, course=$course, relateduser=$relateduser, action='$action', target='$target', other=$other)"
    }

}