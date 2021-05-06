export interface CitiesListResponse {
    content: Cities[];
    totalElements: number;
}
export interface Cities{
    id: number;
    task: string;
    done: boolean
}