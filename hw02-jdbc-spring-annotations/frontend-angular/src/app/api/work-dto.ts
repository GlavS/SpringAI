export type ComposerDto = {
  id: number;
  name: string;
  surname: string;
  country: string;
};

export type InstrumentDto = {
  id: number;
  name: string;
};

export type GenreDto = {
  id: number;
  name: string;
};

export type RecordingDto = {
  id: number;
  performer: string;
  label: string;
  date: number;        // epoch millis
  durationSec: number;
  sourceUrl: string;
};

export type Difficulty = 'EASY' | 'MEDIUM' | 'HARD'; // если есть ещё — допишешь

export type WorkDto = {
  id: number;
  title: string;
  composer: ComposerDto;
  instrument: InstrumentDto;
  genres: GenreDto[];
  recordings: RecordingDto[];
  difficulty: Difficulty;
};

export type LoadState<T> =
  | { status: 'loading' }
  | { status: 'error'; message: string }
  | { status: 'success'; data: T };
