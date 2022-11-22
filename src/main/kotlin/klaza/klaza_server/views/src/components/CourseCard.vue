<template>
    <q-card class="cursor-pointer" @click="setEdit">
        <q-img :src="course.image" class="fit">
            <div v-show="!course.actived" class="fit"></div>

            <div class="absolute-top check">
                <q-checkbox v-model="isSelected" color="c" />
            </div>

            <div class="absolute-bottom text-subtitle1 text-center">
                {{ course.fullName }}
            </div>

            <q-tooltip class="tooltip">
                {{ course.fullName }}
            </q-tooltip>
        </q-img>
    </q-card>
</template>

<script lang="ts">
import { defineComponent } from "vue";

import { CourseDTO } from "src/@types/dtos";

export default defineComponent({
    name: "CourseCard",
    props: {
        course: {
            type: Object as () => CourseDTO,
            required: true,
        },
        selected: {
            type: Array,
            required: true,
        },
    },
    methods: {
        setEdit() {
            this.$emit("set-edit", this.course);
        },
    },
    computed: {
        isSelected: {
            get() {
                return this.selected.includes(this.course.id);
            },
            set(value: boolean) {
                if (value) {
                    this.$emit("add-selected", this.course.id);
                } else {
                    this.$emit("remove-selected", this.course.id);
                }
            },
        },
    },
});
</script>

<style lang="scss" scoped>
.q-card {
    width: 300px;
    height: 170px;
    border-radius: 20px;
}

.q-img__content > div {
    background-color: rgba($color-b, $alpha: 0.7);
    color: $color-w;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}

.q-img__content > div.check {
    background: none;
    color: $color-w;
    padding: 0px;
}
</style>
