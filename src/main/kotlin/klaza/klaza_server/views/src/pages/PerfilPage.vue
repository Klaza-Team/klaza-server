<template>
    <q-page class="column items-center justify-evenly">
        <div class="row">
            <q-avatar>
                <img :src="getAvatar()" />
            </q-avatar>
            <div class="column justify-center q-ml-lg text-w">
                <span class="username"> {{ user.username || "" }} </span>
                <span class="role"> {{ user.role || "" }} </span>
            </div>
        </div>
        <q-card class="bg-b text-w w-80vw r-20 q-pa-lg">
            <q-card-section>
                <div>
                    <span class="title-separator">Contas</span>
                    <q-separator color="w" class="q-my-sm" />

                    <draggable
                        v-model="user.notification_priority"
                        @start="drag = true"
                        @end="drag = false"
                    >
                        <template #item="{ element, index }">
                            <q-list>
                                <account-item :account="element" :index="index" @update-priority="updatePriority"/>
                            </q-list>
                        </template>
                    </draggable>

                    <div class="q-mt-md row justify-center">
                        <q-btn color="a" label="Salvar" />
                    </div>

                </div>
                <div class="q-mt-md">
                    <span class="title-separator">Configuração global</span>
                    <q-separator color="w" class="q-my-sm" />

                    <course-configs :type="'geral'" :loading="loading" @save="saveConfigs" />

                </div>
            </q-card-section>
        </q-card>
    </q-page>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { useUserStore } from "stores/user";
import { useCoursesStore } from "stores/courses";

import draggable from "vuedraggable";
import AccountItem from "src/components/perfil/AccountItem.vue";
import CourseConfigs from "src/components/geral/CourseConfigs.vue";

import { User, UserNotificationApp } from "src/@types/models.js";
import { UserGlobalConfigDTO } from "src/@types/dtos";


export default defineComponent({
    name: "PerfilPage",
    async mounted() { 
        await useUserStore().getUser();
	    await useCoursesStore().getCourses();
    },
    components: {
        draggable,
        AccountItem,
        CourseConfigs
    },
    data() {
        return {
            drag: false,
            user: useUserStore().user as User,
            loading: false,
        };
    },
    methods: {
        updatePriority(priority: any) {
            const p = this.user.notification_priority.find((p) => p.type == priority.type) as UserNotificationApp;
            p.value = priority.value;
            p.priority = (priority.priority == 0) ? priority.index : -1
        },
        getAvatar() {
            return this.user.avatar || "https://cdn.quasar.dev/img/avatar.png";
        },
        // saveAccounts() {}
        saveConfigs(config: UserGlobalConfigDTO) {
            this.loading = true;
            useUserStore().editGlobalConfig(config).finally(() => this.loading = false);
        }
    },
});
</script>

<style lang="scss" scoped>
.q-avatar {
    width: 150px;
    height: 150px;
}

.username {
    font-size: 40px;
}

.role {
    font-size: 25px;
}
</style>
