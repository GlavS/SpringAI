import {ComposerDto} from './model/composer-type';
import {RecordingDto} from './model/recording-type';
import {Difficulty} from './model/difficulty-type';

export type InstrumentDto = {
  id: number;
  name: string;
};

export type GenreDto = {
  id: number;
  name: string;
};

export type WorkDto = {
  id: number;
  title: string;
  composer: ComposerDto;
  instrument: InstrumentDto;
  genres: GenreDto[];
  recordings: RecordingDto[];
  difficulty: Difficulty;
};

//Хранилище состояний UI
export type LoadState<T> =
  | { status: 'loading' }
  | { status: 'error'; message: string }
  | { status: 'success'; data: T };
