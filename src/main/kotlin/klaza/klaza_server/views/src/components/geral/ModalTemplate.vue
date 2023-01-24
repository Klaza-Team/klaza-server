<template>
    <q-dialog v-model="show">
        <q-card>
            <q-card-section
                class="row items-center items-center content-center bg-a header"
            >
                <div class="text-h6 text-w text-center col-grow">
                    {{ title }}
                </div>
                <q-btn
                    icon="fa-solid fa-times"
                    flat
                    round
                    dense
                    color="w"
                    @click="close"
                    v-close-popup
                />
            </q-card-section>
            <q-card-section class="bg-b column">
                <slot />
            </q-card-section>
        </q-card>
    </q-dialog>
</template>

<script lang="ts">

import { defineComponent } from "vue";

export default defineComponent({
    name: "ModalTemplate",
    props: {
        title: {
            type: String,
            required: true,
        },
        value: {
            type: Boolean,
            default: false,
        },
        type: {
            type: String as () => "modal_view" | "modal_create",
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
    },
    methods: {
        close() {

            const newRouterQuery = { ...this.$route.query };
            delete newRouterQuery[this.type];

            this.$router.replace({ query: newRouterQuery })
            this.show = false;
        },
    },
});

</script>

<style lang="scss" scoped>

.q-card {
    max-width: none;
    width: 95%;
    border-radius: 20px;
    background: none;

    .header {
        box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
        z-index: 10;
    }

}

</style>