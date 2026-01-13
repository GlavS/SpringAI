export type RecordingDto = {
  id: number;
  performer: string;
  label: string;
  date: number;        // epoch millis
  durationSec: number;
  sourceUrl: string;
};
