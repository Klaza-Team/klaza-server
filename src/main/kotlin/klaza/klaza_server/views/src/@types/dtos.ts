export interface CourseDTO {
    id: number;
    fullName: string;
    shortName: string;
    image: string;
    actived: boolean;

    user_config: {
        [key: string]: boolean;
    };
}
