<template>
    <modal-template
        v-model="show"
        :title="`Editar configuração de servidor do curso ${course.fullName}`"
        type="modal_create"
    >
        <div class="row justify-center">
            <div class="container-tabs">
                <q-tabs v-model="tab">
                    <q-tab name="discord" label="Discord" no-caps />
                    <q-tab name="telegram" label="Telegram" no-caps />
                </q-tabs>
            </div>
        </div>

        <q-tab-panels v-model="tab" class="bg-b" animated>
            <q-tab-panel name="discord">
                <q-scroll-area>
                    <div class="column">
                        <div>
                            <span class="title-separtor">Seus servers</span>
                            <q-separator color="w" class="q-my-sm" />

                            <q-list
                                :class="
                                    course.discordIntances.user.length == 0
                                        ? 'text-center no-server'
                                        : ''
                                "
                            >
                                <instance-item
                                    v-for="i in course.discordIntances.user"
                                    :key="i.id"
                                    :course="course"
                                    :instance="i"
                                    type="discord_user"
                                />
                                <span
                                    class="no-server"
                                    v-if="
                                        course.discordIntances.user.length == 0
                                    "
                                >
                                    Nenhum servidor entrontrado
                                </span>
                            </q-list>

                            <div class="row justify-center">
                                <q-btn
                                    color="a"
                                    label="Adicionar"
                                    icon="fa-solid fa-plus"
                                    class="btn-add"
                                    @click="addInstance('discord')"
                                    no-caps
                                >
                                    <q-tooltip class="tooltip">
                                        Adicionar novo servidor para notificação
                                    </q-tooltip>
                                </q-btn>
                            </div>
                        </div>
                        <div>
                            <span class="title-separtor"
                                >Servers dos outros</span
                            >
                            <q-separator color="w" class="q-my-sm" />

                            <q-list
                                :class="
                                    course.discordIntances.other.length == 0
                                        ? 'text-center no-server'
                                        : ''
                                "
                            >
                                <instance-item
                                    v-for="i in course.discordIntances.other"
                                    :key="i.id"
                                    :course="course"
                                    :instance="i"
                                    type="discord_other"
                                />
                                <span
                                    class="no-server"
                                    v-if="
                                        course.discordIntances.other.length == 0
                                    "
                                >
                                    Nenhum servidor entrontrado
                                </span>
                            </q-list>
                        </div>
                    </div>
                </q-scroll-area>
            </q-tab-panel>

            <q-tab-panel name="telegram">
                <q-scroll-area>
                    <div class="column">
                        <div>
                            <span class="title-separator">Seus servers</span>
                            <q-separator color="w" class="q-my-sm" />

                            <q-list
                                :class="
                                    course.telegramIntances.user.length == 0
                                        ? 'text-center no-server'
                                        : ''
                                "
                            >
                                <instance-item
                                    v-for="i in course.telegramIntances.user"
                                    :key="i.id"
                                    :course="course"
                                    :instance="i"
                                    type="telegram_user"
                                />
                                <span
                                    class="no-server"
                                    v-if="
                                        course.telegramIntances.user.length == 0
                                    "
                                >
                                    Nenhum servidor entrontrado
                                </span>
                            </q-list>

                            <div class="row justify-center">
                                <q-btn
                                    color="a"
                                    label="Adicionar"
                                    icon="fa-solid fa-plus"
                                    class="btn-add"
                                    @click="addInstance('telegram')"
                                    no-caps
                                >
                                    <q-tooltip class="tooltip">
                                        Adicionar novo servidor para notificação
                                    </q-tooltip>
                                </q-btn>
                            </div>
                        </div>
                        <div>
                            <span class="title-separator"
                                >Servers dos outros</span
                            >
                            <q-separator color="w" class="q-my-sm" />

                            <q-list
                                :class="
                                    course.telegramIntances.other.length == 0
                                        ? 'text-center no-server'
                                        : ''
                                "
                            >
                                <instance-item
                                    v-for="i in course.telegramIntances.other"
                                    :key="i.id"
                                    :course="course"
                                    :instance="i"
                                    type="telegram_other"
                                />
                                <span
                                    class="no-server"
                                    v-if="
                                        course.telegramIntances.other.length ==
                                        0
                                    "
                                >
                                    Nenhum servidor entrontrado
                                </span>
                            </q-list>
                        </div>
                    </div>
                </q-scroll-area>
            </q-tab-panel>
        </q-tab-panels>

        <modal-create-config-server
            v-model="create"
            :course="course"
            :type="tab"
        />
    </modal-template>
</template>

<script lang="ts">
import { CourseDTO } from "src/@types/dtos";
import { defineComponent } from "vue";

import ModalTemplate from "src/components/geral/ModalTemplate.vue";
import InstanceItem from "src/components/servers/InstanceItem.vue";
import ModalCreateConfigServer from "src/components/servers/ModalCreateConfigServer.vue";

export default defineComponent({
    name: "ModalViewServer",
    components: {
        ModalTemplate,
        InstanceItem,
        ModalCreateConfigServer,
    },
    props: {
        value: {
            type: Boolean,
            default: false,
        },
        course: {
            type: Object as () => CourseDTO,
            required: true,
        },
    },
    data() {
        return {
            tab: "discord" as "discord" | "telegram",
            create: false,
        };
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
    },
    methods: {
        addInstance(type: "discord" | "telegram") {
            this.create = true;
            //TODO: implementar
        },
    },
});
</script>

<style lang="scss" scoped>
.container-tabs {
    margin-bottom: 1rem;
    width: 500px;

    .q-tabs {
        background-color: $color-a;
        border-radius: 20px;
        color: $color-w;
    }
}

.q-scrollarea {
    max-width: 100%;
    height: 50vh;
}

.q-list:not(.no-server) {
    border: 1px solid $color-bk;
}

.btn-add {
    margin-top: 12px;
    border-radius: 20px;
}

.no-server {
    color: $color-w;
    font-size: 20px;
    margin-left: 20px;
}
</style>
