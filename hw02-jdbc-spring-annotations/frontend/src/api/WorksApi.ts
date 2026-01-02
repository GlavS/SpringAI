import axios from 'axios'

export type WorkDtoSmall = {
    id: number
    title: string
}

export type Id = number

export type DifficultyDto = 'EASY' | 'MEDIUM' | 'HARD' | 'VERY_HARD'

export type ComposerDto = {
    id: Id
    name: string
    surname: string
    country?: string | null
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
    difficulty: DifficultyDto
    composer: ComposerDto
    instrument: InstrumentDto
    genres: GenreDto[]
    recordings: RecordingDto[]
}


export const worksApi = {
    async list(): Promise<WorkDto[]> {
        const {data} = await axios.get<WorkDto[]>('/api/works')
        return data
    }
}