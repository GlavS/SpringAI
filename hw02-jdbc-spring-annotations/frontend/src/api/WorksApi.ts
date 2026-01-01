import axios from 'axios'

export type WorkDtoSmall = {
    id: number
    title: string
}

type Id = number

export type ComposerDto = {
    id: Id
    name: string
    surname: string
}

export type InstrumentDto = {
    id: Id
    name: string
}

export type GenreDto = {
    id: Id
    name: string
}

export type RecordingDto = {
    id: Id
    performer: string
    label?: string | null
    recordedAt?: string | null
    durationSec?: number | null
    sourceUrl?: string | null
}

export type WorkDto = {
    id: Id
    title: string
    difficulty?: string | null
    composer?: ComposerDto | null
    instrument?: InstrumentDto | null
    genres?: GenreDto[] | null
    recordings?: RecordingDto[] | null
}


export const worksApi = {
    async list(): Promise<WorkDto[]> {
        const {data} = await axios.get<WorkDto[]>('/api/works')
        return data
    }
}