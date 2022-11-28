<template>
    <q-page class="row items-center justify-evenly">
        <div>
            <q-avatar size="xl">
                <!-- //TODO - colocar a imagem do usuário -->
                <img src="https://cdn.quasar.dev/img/avatar.png" />
            </q-avatar>

            <span> {{ user.username }} </span>
            <span> {{ user.role }} </span>
        </div>
    </q-page>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { useUserStore } from "stores/user";

export default defineComponent({
    name: "PerfilPage",
    setup() {
        return {
            user: useUserStore().user,
        };
    },
});
</script>

<style lang="scss" scoped></style>
