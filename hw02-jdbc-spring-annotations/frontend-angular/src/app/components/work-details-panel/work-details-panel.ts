import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

import { WorkDto } from '../../api/data/work-dto';

@Component({
  selector: 'app-work-details-panel',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './work-details-panel.html',
})
export class WorkDetailsPanelComponent {
  @Input({ required: true }) work!: WorkDto;

  fmtDuration(sec?: number | null) {
    if (sec == null) return '';
    const m = Math.floor(sec / 60);
    const s = sec % 60;
    return `${m}:${String(s).padStart(2, '0')}`;
  }
}
