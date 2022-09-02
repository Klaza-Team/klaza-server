package klaza.klaza_server.models

import javax.persistence.*

@Entity
@Table(name = "mdl_user")
class User {

    // Properties

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private var id: Long? = null

    @Column(name = "firstname", length = 100, nullable = false)
    private var firstname: String? = null

    @Column(name = "lastname", length = 100, nullable = false)
    private var lastname: String? = null

    @Column(name = "email", length = 100, nullable = false)
    private var email: String? = null

    @Column(name = "lang", length = 100, nullable = false)
    private var lang: String? = null

    // Methods

    fun getFullName(): String {
        return "$firstname $lastname"
    }

    // Getters and Setters

    fun getId(): Long? {
        return id
    }

    fun setId(id: Long?) {
        this.id = id
    }

    fun getFirstname(): String? {
        return firstname
    }

    fun setFirstname(firstname: String?) {
        this.firstname = firstname
    }

    fun getLastname(): String? {
        return lastname
    }

    fun setLastname(lastname: String?) {
        this.lastname = lastname
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String?) {
        this.email = email
    }

    fun getLang(): String? {
        return lang
    }

    fun setLang(lang: String?) {
        this.lang = lang
    }

    // Override

    override fun toString(): String {
        return "User(id=$id, firstname='$firstname', lastname='$lastname', email='$email', lang='$lang')"
    }

}