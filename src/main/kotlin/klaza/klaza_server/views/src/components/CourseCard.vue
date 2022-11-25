<template>
    <q-card class="cursor-pointer" @click="setEdit">
        <q-img :src="course.image" class="fit">
            <div v-show="!course.actived" class="fit"></div>

            <div v-if="mulitple && type == 'direct'" class="absolute-top check">
                <q-checkbox v-model="isSelected" color="c" />
            </div>

            <div v-if="type == 'server'" class="absolute-top indicator">
                <svg
                    width="31"
                    height="35"
                    viewBox="0 0 31 35"
                    fill="none"
                    xmlns="http://www.w3.org/2000/svg"
                    :class="hasDiscod ? 'text-w' : 'text-bk'"
                >
                    <path
                        d="M20.3175 16.625C20.3175 17.6925 19.53 18.5675 18.5325 18.5675C17.5525 18.5675 16.7475 17.6925 16.7475 16.625C16.7475 15.5575 17.535 14.6825 18.5325 14.6825C19.53 14.6825 20.3175 15.5575 20.3175 16.625ZM12.145 14.6825C11.1475 14.6825 10.36 15.5575 10.36 16.625C10.36 17.6925 11.165 18.5675 12.145 18.5675C13.1425 18.5675 13.93 17.6925 13.93 16.625C13.9475 15.5575 13.1425 14.6825 12.145 14.6825ZM30.625 3.605V35C26.2162 31.1039 27.6262 32.3936 22.505 27.6325L23.4325 30.87H3.5875C1.61 30.87 0 29.26 0 27.265V3.605C0 1.61 1.61 0 3.5875 0H27.0375C29.015 0 30.625 1.61 30.625 3.605ZM25.6375 20.195C25.6375 14.56 23.1175 9.9925 23.1175 9.9925C20.5975 8.1025 18.2 8.155 18.2 8.155L17.955 8.435C20.93 9.345 22.3125 10.6575 22.3125 10.6575C18.1555 8.37915 13.2724 8.37874 9.24 10.15C8.5925 10.4475 8.2075 10.6575 8.2075 10.6575C8.2075 10.6575 9.66 9.275 12.81 8.365L12.635 8.155C12.635 8.155 10.2375 8.1025 7.7175 9.9925C7.7175 9.9925 5.1975 14.56 5.1975 20.195C5.1975 20.195 6.6675 22.7325 10.535 22.855C10.535 22.855 11.1825 22.0675 11.7075 21.4025C9.485 20.7375 8.645 19.3375 8.645 19.3375C8.90244 19.5177 9.32695 19.7513 9.3625 19.775C12.3163 21.4292 16.5121 21.9711 20.2825 20.3875C20.895 20.16 21.5775 19.8275 22.295 19.355C22.295 19.355 21.42 20.79 19.1275 21.4375C19.6525 22.1025 20.2825 22.855 20.2825 22.855C24.15 22.7325 25.6375 20.195 25.6375 20.195Z"
                        fill="currentColor"
                    />
                </svg>

                <svg
                    width="43"
                    height="35"
                    viewBox="0 0 43 35"
                    fill="none"
                    xmlns="http://www.w3.org/2000/svg"
                    :class="hasTelegram ? 'text-w' : 'text-bk'"
                >
                    <path
                        d="M42.2402 3.18629L35.9423 32.8872C35.4671 34.9835 34.228 35.5052 32.4672 34.5176L22.8712 27.4464L18.241 31.8997C17.7285 32.4121 17.3 32.8407 16.3124 32.8407L17.0019 23.0677L34.787 6.99674C35.5603 6.30732 34.6193 5.92534 33.5852 6.61476L11.5983 20.4591L2.13275 17.4964C0.073806 16.8536 0.03654 15.4375 2.56131 14.4499L39.585 0.186385C41.2993 -0.456452 42.7992 0.568361 42.2402 3.18629Z"
                        fill="currentColor"
                    />
                </svg>
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
        mulitple: {
            type: Boolean,
            required: true,
        },
        type: {
            type: String as () => "direct" | "server",
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
        hasDiscod() {
            return this.course.discordIntances.length > 0;
        },
        hasTelegram() {
            return this.course.telegramIntances.length > 0;
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

.q-img__content > div.indicator {
    display: flex;
    gap: 20px;
    color: $color-w;
    padding: 5px;
    width: fit-content;
    height: 45px;
    margin: 5px auto;
    border-radius: 5px;
}
</style>
