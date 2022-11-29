<template>
    <q-page class="column items-center justify-evenly">
        <div class="row">
            <q-avatar>
                <!-- //TODO - colocar a imagem do usuário -->
                <img
                    src="https://media.tenor.com/kB_HPWQFeEkAAAAC/nekopara-cinnamon.gif"
                />
            </q-avatar>
            <div class="column justify-center q-ml-lg text-w">
                <span class="username"> {{ user.username }} </span>
                <span class="role"> {{ user.role }} </span>
            </div>
        </div>
        <q-card class="bg-b text-w w-80vw">
            <q-card-section>
                <div>
                    <span class="title-separator">Contas</span>
                    <q-separator color="w" class="q-my-sm" />

                    <draggable
                        v-model="user.notification_priority"
                        @start="drag = true"
                        @end="drag = false"
                    >
                        <template #item="{ element }">
                            <q-list bordered>
                                <account-item :account="element" />
                            </q-list>
                        </template>
                    </draggable>
                </div>
            </q-card-section>
        </q-card>
    </q-page>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { useUserStore } from "stores/user";

import draggable from "vuedraggable";
import AccountItem from "src/components/perfil/AccountItem.vue";

export default defineComponent({
    name: "PerfilPage",
    components: {
        draggable,
        AccountItem,
    },
    data() {
        return {
            drag: false,
        };
    },
    setup() {
        return {
            user: useUserStore().user,
        };
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
