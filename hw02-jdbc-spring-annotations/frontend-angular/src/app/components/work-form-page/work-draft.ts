import {WorkDto} from '../../api/data/work-dto';

export type WorkDraft = {
  title: string,
  instrument: string,
  difficulty: string
}

export function toDraft(dto: WorkDto): WorkDraft {
  return {
    title: dto.title,
    instrument: dto.instrument.name,
    difficulty: dto.difficulty
  };
}

export function toCreateRequest(){}
export function toUpdateRequest(){}
