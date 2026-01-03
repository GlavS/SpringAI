<script setup lang="ts">
import type {WorkDto} from "@/api/WorksApi";

defineProps<{
  work: WorkDto
  expanded: boolean
}>()

const emit = defineEmits<{
  (e: 'toggle', id: number): void
}>()

function fmtDuration(sec?: number | null) {
  if (sec == null) return ''
  const m = Math.floor(sec / 60)
  const s = sec % 60
  return `${m}:${String(s).padStart(2, '0')}`
}
</script>

<template>
  <tr @click="emit('toggle', work.id)" style="cursor: pointer">
    <td>{{ work.id }}</td>
    <td>{{ work.title }}</td>
    <td>{{ work.composer?.name }} {{ work.composer?.surname }}</td>
    <td>{{ work.instrument?.name }}</td>
    <td>{{ work.difficulty }}</td>
    <td class="text-end">
      <button class="btn btn-sm btn-outline-secondary" @click.stop="emit('toggle', work.id)">
        {{ expanded ? 'Hide' : 'Details' }}
      </button>
    </td>
  </tr>

  <tr v-if="expanded" class="bg-light">
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
              — <a :href="r.sourceUrl" target="_blank" rel="noreferrer">source</a>
            </span>
          </li>
        </ul>
      </div>
    </td>
  </tr>
</template>

