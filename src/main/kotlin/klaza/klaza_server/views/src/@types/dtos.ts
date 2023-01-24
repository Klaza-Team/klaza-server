export interface CourseDTO {

    id: number
    fullName: string
    shortName: string
    image: string
    discordIntances: DiscordInstanceDTO[]
    telegramIntances: TelegramInstanceDTO[]

}

export interface DiscordInstanceDTO {

    id: number
    guild_id: string
    channel_id: string
    config: UserCourseConfigDTO
    creator_id: number

}

export interface UserCourseConfigDTO {

    use_global: boolean
    notify_create_content: boolean
    notify_edit_content: boolean
    notify_delete_content: boolean
    notify_deadline_2_days: boolean
    notify_deadline_1_day: boolean
    notify_deadline: boolean
    notify_send_assignment: boolean
    notify_receive_message: boolean
    notify_receive_comment: boolean
    notify_delete_comment: boolean

}

export interface TelegramInstanceDTO {

    id: number
    channel_id: string
    config: UserCourseConfigDTO
    creator_id: number

}

export interface UserDTO {
    id: number
    username: string
    email: string
    avatar: string
    role: string
    notification_priority: UserNotificationAppDTO[]
    global_config: UserGlobalConfigDTO
}

export interface UserGlobalConfigDTO {
    
    notify_create_content: boolean
    notify_edit_content: boolean
    notify_delete_content: boolean
    notify_deadline_2_days: boolean
    notify_deadline_1_day: boolean
    notify_deadline: boolean
    notify_send_assignment: boolean
    notify_receive_message: boolean
    notify_receive_comment: boolean
    notify_delete_comment: boolean

}

export interface UserNotificationAppDTO {
    id: number
    type: string
    priority: number
    value: string
}