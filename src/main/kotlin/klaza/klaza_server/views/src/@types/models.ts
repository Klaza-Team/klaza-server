import { UserDTO } from "./dtos";

export interface Course {
    id: number;
    fullName: string;
    shortName: string;
    image: string;
    actived: boolean;

    user_config: CourseConfig;

    discordIntances: DiscordInstance;
    telegramIntances: TelegramInstance;
}

export interface CourseConfig {
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

export interface GlobalConfig {
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

export interface DiscordInstance {
    user: UserCourseDiscordConfig[]
    other: UserCourseDiscordConfig[]
}

export interface TelegramInstance {
    user: UserCourseTelegramConfig[]
    other: UserCourseTelegramConfig[]
}

export interface User {
    id: number;
    username: string;
    email: string;
    avatar: string
    role: string
    globalConfig: GlobalConfig
    notification_priority: UserNotificationApp[]
}

export interface UserCourseTelegramConfig {
    id: number
    channel_id: string
    config: CourseConfig
    creator: UserDTO
}

export interface UserCourseDiscordConfig {
    id: number;
    guild_id: string;
    channel_id: string;
    config: CourseConfig;
    creator: UserDTO
}

export interface UserDiscordTelegramServer {
    id: string;
    channel: string;
    name: string;
}

export interface UserNotificationApp {
    id: number;
    type: "discord" | "telegram" | "whatsapp";
    priority: number;
    value: string;
}