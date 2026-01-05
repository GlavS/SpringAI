<script lang="ts" setup>
import {WorkDto} from "@/api/WorksApi";

defineProps<{
  work: WorkDto
}>()

function fmtDuration(sec?: number | null) {
  if (sec == null) return ''
  const m = Math.floor(sec / 60)
  const s = sec % 60
  return `${m}:${String(s).padStart(2, '0')}`
}

</script>

<template>

  <td colspan="6">
    <div class="mb-2">
      <strong>Genres:</strong>
      <span v-if="!work.genres?.length" class="text-muted"> none</span>
      <span v-else>
          <span
              v-for="g in work.genres"
              :key="g.id"
              class="badge text-bg-secondary me-1"
          >
            {{ g.name }}
          </span>
        </span>
    </div>

    <div>
      <strong>Recordings:</strong>
      <div v-if="!work.recordings?.length" class="text-muted">none</div>
      <ul v-else class="mb-0">
        <li v-for="r in work.recordings" :key="r.id">
          {{ r.performer }}
          <span v-if="r.label"> • {{ r.label }}</span>
          <span v-if="r.recordedAt"> ({{ r.recordedAt }})</span>
          <span v-if="r.durationSec != null"> • {{ fmtDuration(r.durationSec) }}</span>
          <span v-if="r.sourceUrl">
              — <a :href="r.sourceUrl" rel="noreferrer" target="_blank">source</a>
            </span>
        </li>
      </ul>
    </div>
  </td>
</template>