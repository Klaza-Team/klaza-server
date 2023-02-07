package klaza.klaza_server.classes

import java.util.*

class KlazyImages {

    companion object {

        fun getAcenando(): ByteArray {
            return KlazyImages::class.java.getResource("/klazy/klazy_acenando_edit.png")?.readBytes()!!
        }

        fun getAcenandoBase64(): String {
            return Base64.getEncoder().encodeToString(this.getAcenando())
        }

        fun getAcenandoURL(): String {
            return this.getAcenandoBase64().let { "data:image/png;base64,$it" }
        }

        fun getBrava(): ByteArray {
            return KlazyImages::class.java.getResource("/klazy/klazy_brava_edit.png")?.readBytes()!!
        }

        fun getBravaBase64(): String {
            return Base64.getEncoder().encodeToString(this.getBrava())
        }

        fun getBravaURL(): String {
            return this.getBravaBase64().let { "data:image/png;base64,$it" }
        }

        fun getCelular(): ByteArray {
            return KlazyImages::class.java.getResource("/klazy/klazy_celular_edit.png")?.readBytes()!!
        }

        fun getCelularBase64(): String {
            return Base64.getEncoder().encodeToString(this.getCelular())
        }

        fun getCelularURL(): String {
            return this.getCelularBase64().let { "data:image/png;base64,$it" }
        }

        fun getEscrevendo(): ByteArray {
            return KlazyImages::class.java.getResource("/klazy/klazy_escrevendo_edit.png")?.readBytes()!!
        }

        fun getEscrevendoBase64(): String {
            return Base64.getEncoder().encodeToString(this.getEscrevendo())
        }

        fun getEscrevendoURL(): String {
            return this.getEscrevendoBase64().let { "data:image/png;base64,$it" }
        }

        fun getFrente(): ByteArray {
            return KlazyImages::class.java.getResource("/klazy/klazy_frente_edit.png")?.readBytes()!!
        }

        fun getFrenteBase64(): String {
            return Base64.getEncoder().encodeToString(this.getFrente())
        }

        fun getFrenteURL(): String {
            return this.getFrenteBase64().let { "data:image/png;base64,$it" }
        }

        fun getLendo(): ByteArray {
            return KlazyImages::class.java.getResource("/klazy/klazy_lendo_edit.png")?.readBytes()!!
        }

        fun getLendoBase64(): String {
            return Base64.getEncoder().encodeToString(this.getLendo())
        }

        fun getLendoURL(): String {
            return this.getLendoBase64().let { "data:image/png;base64,$it" }
        }

        fun getMegafone(): ByteArray {
            return KlazyImages::class.java.getResource("/klazy/klazy_megafone_edit.png")?.readBytes()!!
        }

        fun getMegafoneBase64(): String {
            return Base64.getEncoder().encodeToString(this.getMegafone())
        }

        fun getMegafoneURL(): String {
            return this.getMegafoneBase64().let { "data:image/png;base64,$it" }
        }

        fun getPapeis(): ByteArray {
            return KlazyImages::class.java.getResource("/klazy/klazy_papeis_edit.png")?.readBytes()!!
        }

        fun getPapeisBase64(): String {
            return Base64.getEncoder().encodeToString(this.getPapeis())
        }

        fun getPapeisURL(): String {
            return this.getPapeisBase64().let { "data:image/png;base64,$it" }
        }

        fun getPensando(): ByteArray {
            return KlazyImages::class.java.getResource("/klazy/klazy_pensando_edit.png")?.readBytes()!!
        }

        fun getPensandoBase64(): String {
            return Base64.getEncoder().encodeToString(this.getPensando())
        }

        fun getPensandoURL(): String {
            return this.getPensandoBase64().let { "data:image/png;base64,$it" }
        }

        fun getRelogio(): ByteArray {
            return KlazyImages::class.java.getResource("/klazy/klazy_relogio_edit.png")?.readBytes()!!
        }

        fun getRelogioBase64(): String {
            return Base64.getEncoder().encodeToString(this.getRelogio())
        }

        fun getRelogioURL(): String {
            return this.getRelogioBase64().let { "data:image/png;base64,$it" }
        }

    }

}