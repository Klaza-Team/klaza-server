export interface CourseDTO {
    id: number;
    fullName: string;
    shortName: string;
    image: string;
    actived: boolean;

    user_config: CourseConfigDTO;

    discordIntances: DiscordInstanceDTO[];
    telegramIntances: TelegramInstanceDTO[];
}

export interface CourseConfigDTO {
    [key: string]: boolean;
}

export interface DiscordInstanceDTO {
    user: {
        id: number;
        guild_id: string;
        channel_id: string;
        config: CourseConfigDTO;
    }[];
    other: {
        id: number;
        guild_id: string;
        channel_id: string;
        config: CourseConfigDTO;
    }[];
}

export interface TelegramInstanceDTO {
    user: {
        id: number;
        channel_id: string;
        config: CourseConfigDTO;
    }[];
    other: {
        id: number;
        channel_id: string;
        config: CourseConfigDTO;
    }[];
}
