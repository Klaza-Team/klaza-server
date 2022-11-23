<template>
    <q-dialog v-model="show">
        <q-card>
            <q-card-section
                class="row items-center items-center content-center bg-a"
            >
                <div class="text-h6 text-w text-center col-grow">
                    Editar configurações do curso {{ localCourse.fullName }}
                </div>
                <q-btn
                    icon="fa-solid fa-times"
                    flat
                    round
                    dense
                    color="w"
                    v-close-popup
                />
            </q-card-section>
            <q-card-section class="bg-b">
                <div class="text-h6 text-w text-center">
                    {{ localCourse.fullName }}
                </div>
                <div class="full-width column content-center">
                    <div class="text-w row justify-between items-center" style="width: 370px;">
                        <span>Ativar notificações para esse curso</span>
                        <q-toggle
                            v-model="localCourse.actived"
                            color="bk"
                            checked-icon="fa-solid fa-check"
                            unchecked-icon="fa-solid fa-times"
                        />
                    </div>
                    <div class="text-w row justify-between items-center" style="width: 370px;">
                        <span>Usar configuração global para esse curso</span>
                        <q-toggle
                            v-model="localCourse.user_config['use_global']"
                            color="bk"
                            checked-icon="fa-solid fa-check"
                            unchecked-icon="fa-solid fa-times"
                        />
                    </div>
                </div>
                <q-scroll-area>
                    <div class="row justify-center gap-15">
                        <fieldset class="config-group">
                            <legend>Conteudo</legend>

                            <div
                                class="full-width row justify-between items-center text-w"
                            >
                                <span class="col-grow"
                                    >Notificar criação de conteudo</span
                                >
                                <q-toggle
                                    v-model="
                                        localCourse.user_config[
                                            'notify_create_content'
                                        ]
                                    "
                                    color="bk"
                                    checked-icon="fa-solid fa-check"
                                    unchecked-icon="fa-solid fa-times"
                                />
                            </div>

                            <div
                                class="full-width row justify-between items-center text-w"
                            >
                                <span>Notificar edição de conteudo</span>
                                <q-toggle
                                    v-model="
                                        localCourse.user_config[
                                            'notify_edit_content'
                                        ]
                                    "
                                    color="bk"
                                    checked-icon="fa-solid fa-check"
                                    unchecked-icon="fa-solid fa-times"
                                />
                            </div>

                            <div
                                class="full-width row justify-between items-center text-w"
                            >
                                <span>Notificar exclusão de conteudo</span>
                                <q-toggle
                                    v-model="
                                        localCourse.user_config[
                                            'notify_delete_content'
                                        ]
                                    "
                                    color="bk"
                                    checked-icon="fa-solid fa-check"
                                    unchecked-icon="fa-solid fa-times"
                                />
                            </div>
                        </fieldset>
                        <fieldset class="config-group">
                            <legend>Prazo</legend>

                            <div
                                class="full-width row justify-between items-center text-w"
                            >
                                <span>Notificar prazo de entraga (2 dias)</span>
                                <q-toggle
                                    v-model="
                                        localCourse.user_config[
                                            'notify_deadline_2_days'
                                        ]
                                    "
                                    color="bk"
                                    checked-icon="fa-solid fa-check"
                                    unchecked-icon="fa-solid fa-times"
                                />
                            </div>

                            <div
                                class="full-width row justify-between items-center text-w"
                            >
                                <span>Notificar prazo de entraga (1 dia)</span>
                                <q-toggle
                                    v-model="
                                        localCourse.user_config[
                                            'notify_deadline_1_day'
                                        ]
                                    "
                                    color="bk"
                                    checked-icon="fa-solid fa-check"
                                    unchecked-icon="fa-solid fa-times"
                                />
                            </div>

                            <div
                                class="full-width row justify-between items-center text-w"
                            >
                                <span>Notificar prazo de entraga (no dia)</span>
                                <q-toggle
                                    v-model="
                                        localCourse.user_config[
                                            'notify_deadline'
                                        ]
                                    "
                                    color="bk"
                                    checked-icon="fa-solid fa-check"
                                    unchecked-icon="fa-solid fa-times"
                                />
                            </div>

                            <div
                                class="full-width row justify-between items-center text-w"
                            >
                                <span>Notificar envio de entraga</span>
                                <q-toggle
                                    v-model="
                                        localCourse.user_config[
                                            'notify_send_assignment'
                                        ]
                                    "
                                    color="bk"
                                    checked-icon="fa-solid fa-check"
                                    unchecked-icon="fa-solid fa-times"
                                />
                            </div>
                        </fieldset>
                        <fieldset class="config-group">
                            <legend>Mensagens e Comentarios</legend>

                            <div
                                class="full-width row justify-between items-center text-w"
                            >
                                <span>Notificar recebimento de mensagens</span>
                                <q-toggle
                                    v-model="
                                        localCourse.user_config[
                                            'notify_receive_message'
                                        ]
                                    "
                                    color="bk"
                                    checked-icon="fa-solid fa-check"
                                    unchecked-icon="fa-solid fa-times"
                                />
                            </div>

                            <div
                                class="full-width row justify-between items-center text-w"
                            >
                                <span
                                    >Notificar recebimento de comentarios</span
                                >
                                <q-toggle
                                    v-model="
                                        localCourse.user_config[
                                            'notify_receive_comment'
                                        ]
                                    "
                                    color="bk"
                                    checked-icon="fa-solid fa-check"
                                    unchecked-icon="fa-solid fa-times"
                                />
                            </div>

                            <div
                                class="full-width row justify-between items-center text-w"
                            >
                                <span>Notificar exclusão de comentarios</span>
                                <q-toggle
                                    v-model="
                                        localCourse.user_config[
                                            'notify_delete_comment'
                                        ]
                                    "
                                    color="bk"
                                    checked-icon="fa-solid fa-check"
                                    unchecked-icon="fa-solid fa-times"
                                />
                            </div>
                        </fieldset>
                    </div>
                </q-scroll-area>
            </q-card-section>
        </q-card>
    </q-dialog>
</template>

<script lang="ts">
import { CourseDTO } from "src/@types/dtos";
import { defineComponent, PropType } from "vue";

export default defineComponent({
    name: "ModalEdit",
    props: {
        value: {
            type: Boolean,
            default: false,
        },
        course: {
            type: Object as PropType<CourseDTO>,
            default: () => ({}),
            required: true,
        },
    },
    computed: {
        show: {
            get() {
                return this.value;
            },
            set(value: boolean) {
                this.$emit("input", value);
            },
        },
        localCourse: {
            get() {
                return this.course;
            },
            set(value: CourseDTO) {
                //TODO - da update no store
                this.$emit("update:course", value);
            },
        },
    },
});
</script>

<style lang="scss" scoped>
.q-card {
    max-width: none;
    width: 95%;
    border-radius: 20px;
}

.config-group {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    border: 1px solid $color-w;
    color: $color-w;
    border-radius: 20px;
    padding: 10px;
    flex-grow: 1;

    legend {
        padding: 0 5px;
    }
}

.q-scrollarea {
    width: 100%; 
    height: 300px;



}

</style>

<style lang="scss">

.q-scrollarea {
    .q-scrollarea__content {
        display: flex;
        justify-content: space-around;
        align-items: center;
    }
    .q-scrollarea__content > div {
        width: 95%;
    }
}


</style>
