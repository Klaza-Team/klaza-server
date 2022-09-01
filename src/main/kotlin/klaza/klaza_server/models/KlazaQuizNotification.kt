package klaza.klaza_server.models

import javax.persistence.*

@Entity
@Table(name = "mdl_klaza_quiz_notification")
class KlazaQuizNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private var id: Long? = null

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quiz_id", nullable = false)
    private var quiz: Quiz? = null

    fun getId(): Long? {
        return id
    }

    fun setId(id: Long?) {
        this.id = id
    }

    fun getQuiz(): Quiz? {
        return quiz
    }

    fun setQuiz(quiz: Quiz) {
        this.quiz = quiz
    }

    override fun toString(): String {
        return "KlazaQuizNotification(id=$id, quiz=$quiz)"
    }

}