import type {ComposerDto, GenreDto, InstrumentDto, RecordingDto, WorkDto} from "@/api/WorksApi";

const instrument: InstrumentDto = {
    id: 1,
    name: "Test instrument 1"
}

const composer: ComposerDto = {
    id: 1,
    name: "Name_1",
    surname: "Surname_1"
}

const genres: GenreDto[] = [
    {
        id: 1,
        name: "Classic"
    },
    {
        id: 2,
        name: "Baroque"
    }
]

const recordings: RecordingDto[] = [
    {
        id: 1,
        performer: "Test performer 1",
        label: "Test label 1",
        recordedAt: "20.03.1970",
        durationSec: 1800,
        sourceUrl: "http://test.com"
    },
    {
        id: 2,
        performer: "Test performer 2",
        label: "Test label 2",
        recordedAt: "21.03.1970",
        durationSec: 800,
        sourceUrl: "http://test.com"
    }
]

export const mockValues: WorkDto[] = [
    {
        id: 1,
        title: "Test title 1",
        composer: composer,
        instrument: instrument,
        difficulty: "HARD",
        genres: genres,
        recordings: recordings
    },
    {
        id: 2,
        title: "Test title 2",
        composer: composer,
        instrument: instrument,
        difficulty: "EASY",
        genres: genres,
        recordings: recordings
    },
    {
        id: 3,
        title: "Test title 3",
        composer: composer,
        instrument: instrument,
        difficulty: "MEDIUM",
        genres: genres,
        recordings: recordings
    }
]
