import axios from 'axios'

export type WorkDto = {
    id: number
    title: string
}

export const worksApi = {
    async list(): Promise<WorkDto[]> {
        const {data} = await axios.get<WorkDto[]>('/api/works')
        return data
    }
}