package klaza.klaza_server.models

import javax.persistence.*

@Entity
@Table(name = "mdl_user_info_data")
class UserInfoData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private var id: Long? = null

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userid", referencedColumnName = "id")
    private var user: User? = null

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fieldid", referencedColumnName = "id")
    private var field: UserInfoField? = null

    @Column(name = "data", columnDefinition = "LONGTEXT", nullable = false)
    private var data: String? = null

    @Column(name = "dataformat", columnDefinition = "TINYINT", nullable = false)
    private var dataFormat: Int? = null

    fun getId(): Long? {
        return id
    }

    fun setId(id: Long?) {
        this.id = id
    }

    fun getUser(): User? {
        return user
    }

    fun setUser(user: User?) {
        this.user = user
    }

    fun getField(): UserInfoField? {
        return field
    }

    fun setField(field: UserInfoField?) {
        this.field = field
    }

    fun getData(): String? {
        return data
    }

    fun setData(data: String?) {
        this.data = data
    }

    fun getDataFormat(): Int? {
        return dataFormat
    }

    fun setDataFormat(dataFormat: Int?) {
        this.dataFormat = dataFormat
    }

    override fun toString(): String {
        return "UserInfoData(id=$id, user=$user, field=$field, data=$data, dataFormat=$dataFormat)"
    }

}