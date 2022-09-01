package klaza.klaza_server.models

import javax.persistence.*

@Entity
@Table(name = "klaza_quiz_notification")
open class KlazaQuizNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null
}