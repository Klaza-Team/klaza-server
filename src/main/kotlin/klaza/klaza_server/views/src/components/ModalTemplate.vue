<template>
    <q-dialog v-model="show">
        <q-card>
            <q-card-section
                class="row items-center items-center content-center bg-a"
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
                    v-close-popup
                />
            </q-card-section>
            <q-card-section class="bg-b">
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
});

</script>

<style lang="scss" scoped>

.q-card {
    max-width: none;
    width: 95%;
    border-radius: 20px;
    background: none;
}

</style>