export type Difficulty =
  'EASY'
  | 'MEDIUM'
  | 'HARD'
  | 'VERY_HARD';

export const DIFFICULTIES: readonly Difficulty[] = [
  'EASY', 'MEDIUM', 'HARD', 'VERY_HARD',
] as const;

export const DIFFICULTY_LABELS: Record<Difficulty, string> = {
  EASY: 'Easy',
  MEDIUM: 'Medium',
  HARD: 'Hard',
  VERY_HARD: 'Very hard',
};
