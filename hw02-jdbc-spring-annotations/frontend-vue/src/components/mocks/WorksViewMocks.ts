import type {
    WorkDto,
    ComposerDto,
    InstrumentDto,
    GenreDto,
    RecordingDto
} from "@/api/WorksApi"

// ---------- shared dictionaries ----------

const piano: InstrumentDto = { id: 1, name: "Piano" }
const harpsichord: InstrumentDto = { id: 2, name: "Harpsichord" }
const violin: InstrumentDto = { id: 3, name: "Violin" }

const bach: ComposerDto = {
    id: 1,
    name: "Johann Sebastian",
    surname: "Bach",
    country: "DE"
}

const mozart: ComposerDto = {
    id: 2,
    name: "Wolfgang Amadeus",
    surname: "Mozart",
    country: "AT"
}

const beethoven: ComposerDto = {
    id: 3,
    name: "Ludwig van",
    surname: "Beethoven",
    country: "DE"
}

const baroque: GenreDto = { id: 1, name: "Baroque" }
const classical: GenreDto = { id: 2, name: "Classical" }
const romantic: GenreDto = { id: 3, name: "Romantic" }
const sacred: GenreDto = { id: 4, name: "Sacred music" }

// ---------- helpers ----------

function toMillis(date: string): number {
    return new Date(date + "T00:00:00Z").getTime()
}

// ---------- recordings ----------

const gouldGoldberg: RecordingDto[] = [
    {
        id: 1,
        performer: "Glenn Gould",
        label: "Columbia",
        date: toMillis("1955-06-01"),
        durationSec: 2280,
        sourceUrl: "https://example.com/gould-1955"
    },
    {
        id: 2,
        performer: "Glenn Gould",
        label: "CBS",
        date: toMillis("1981-04-10"),
        durationSec: 3080,
        sourceUrl: "https://example.com/gould-1981"
    }
]

const perahiaMozart: RecordingDto[] = [
    {
        id: 3,
        performer: "Murray Perahia",
        label: "Sony Classical",
        date: toMillis("1990-05-12"),
        durationSec: 1320,
        sourceUrl: "https://example.com/perahia-mozart"
    }
]

const leonhardtBach: RecordingDto[] = [
    {
        id: 4,
        performer: "Gustav Leonhardt",
        label: "Teldec",
        date: toMillis("1978-09-20"),
        durationSec: 1560,
        sourceUrl: "https://example.com/leonhardt-bach"
    }
]

const karajanBeethoven: RecordingDto[] = [
    {
        id: 5,
        performer: "Herbert von Karajan",
        label: "Deutsche Grammophon",
        date: toMillis("1963-11-03"),
        durationSec: 2520,
        sourceUrl: "https://example.com/karajan-beethoven"
    }
]

// ---------- works ----------

export const mockWorks: WorkDto[] = [
    {
        id: 1,
        title: "Goldberg Variations BWV 988",
        composer: bach,
        instrument: harpsichord,
        difficulty: "VERY_HARD",
        genres: [baroque],
        recordings: gouldGoldberg
    },
    {
        id: 2,
        title: "Well-Tempered Clavier, Book I",
        composer: bach,
        instrument: harpsichord,
        difficulty: "VERY_HARD",
        genres: [baroque],
        recordings: leonhardtBach
    },
    {
        id: 3,
        title: "Piano Sonata No. 11 K.331",
        composer: mozart,
        instrument: piano,
        difficulty: "MEDIUM",
        genres: [classical],
        recordings: perahiaMozart
    },
    {
        id: 4,
        title: "Piano Sonata No. 14 Op.27 No.2 (Moonlight)",
        composer: beethoven,
        instrument: piano,
        difficulty: "HARD",
        genres: [classical, romantic],
        recordings: karajanBeethoven
    },
    {
        id: 5,
        title: "Mass in B minor BWV 232",
        composer: bach,
        instrument: violin,
        difficulty: "VERY_HARD",
        genres: [baroque, sacred],
        recordings: []
    }
]
