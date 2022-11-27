export interface CourseDTO {
    id: number;
    fullName: string;
    shortName: string;
    image: string;
    actived: boolean;

    user_config: CourseConfigDTO;

    discordIntances: DiscordInstanceDTO;
    telegramIntances: TelegramInstanceDTO;
}

export interface CourseConfigDTO {
    use_global: boolean;
    notify_create_content: boolean;
    notify_edit_content: boolean;
    notify_delete_content: boolean;
    notify_deadline_2_days: boolean;
    notify_deadline_1_day: boolean;
    notify_deadline: boolean;
    notify_send_assignment: boolean;
    notify_receive_message: boolean;
    notify_receive_comment: boolean;
    notify_delete_comment: boolean;
}

export interface DiscordInstanceDTO {
    user: UserCourseDiscordConfigDTO[]
    other: UserCourseDiscordConfigDTO[]
}

export interface TelegramInstanceDTO {
    user: UserCourseTelegramConfigDTO[]
    other: UserCourseTelegramConfigDTO[]
}

export interface UserDTO {
    id: number;
    username: string;
    email: string;
    avatar: string
    role: string
    courses: CourseDTO[]
    globalConfig: CourseConfigDTO
}

export interface UserCourseTelegramConfigDTO {
    id: number
    channel_id: string
    config: CourseConfigDTO
    creator: UserDTO
}

export interface UserCourseDiscordConfigDTO {
    id: number;
    guild_id: string;
    channel_id: string;
    config: CourseConfigDTO;
    creator: UserDTO
}

export interface UserDiscordTelegramServer {
    id: string;
    name: string;
}