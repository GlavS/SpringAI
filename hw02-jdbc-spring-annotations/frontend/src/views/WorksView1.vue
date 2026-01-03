<script lang="ts" setup>
import { onMounted, ref } from 'vue'
import type { WorkDto } from "@/api/WorksApi"
import { worksApi } from "@/api/WorksApi"
import { mockWorks } from "@/views/mocks/WorksViewMocks"

type LoadState =
    | { status: 'loading' }
    | { status: 'error'; message: string }
    | { status: 'success'; data: WorkDto[] }

const state = ref<LoadState>({status: 'loading'})
const expandedId = ref<number | null>(null)

function toggle(id: number) {
  expandedId.value = expandedId.value === id ? null : id
}

function fmtDuration(sec?: number | null) {
  if (sec == null) return ''
  const m = Math.floor(sec / 60)
  const s = sec % 60
  return `${m}:${String(s).padStart(2, '0')}`
}

function getErrorMessage(e: unknown): string {
  return e instanceof Error ? e.message : String(e)
}

onMounted(async () => {
  state.value = {status: 'loading'}
  try {
    // const data = mockWorks //запрос из моков
    const data = await worksApi.list()
    state.value = {status: 'success', data: data}
  } catch (e) {
    state.value = {status: 'error', message: getErrorMessage(e)}
  }
})
</script>

<template>
  <div class="container py-4">
    <h3 class="mb-3">Works</h3>

    <div v-if="state.status === 'error'" class="alert alert-danger">{{ state.message }}</div>
    <div v-else-if="state.status === 'loading'">Loading...</div>

    <table v-else class="table table-striped align-middle">
      <thead>
      <tr>
        <th style="width: 80px;">ID</th>
        <th>Title</th>
        <th>Composer</th>
        <th>Instrument</th>
        <th>Difficulty</th>
        <th style="width: 120px;"></th>
      </tr>
      </thead>

      <tbody>
      <template v-for="w in state.data" :key="w.id">
        <tr>
          <td>{{ w.id }}</td>
          <td>{{ w.title }}</td>
          <td>{{ w.composer?.name }} {{ w.composer?.surname }}</td>
          <td>{{ w.instrument?.name }}</td>
          <td>{{ w.difficulty }}</td>
          <td class="text-end">
            <button class="btn btn-sm btn-outline-secondary" @click="toggle(w.id)">
              {{ expandedId === w.id ? 'Hide' : 'Details' }}
            </button>
          </td>
        </tr>

        <tr class="bg-light" v-if="expandedId === w.id">
          <td colspan="6">
            <div class="mb-2">
              <strong>Genres:</strong>
              <span v-if="!w.genres?.length" class="text-muted"> none</span>
              <span v-else>
                <span v-for="g in w.genres" :key="g.id" class="badge text-bg-secondary me-1">
                  {{ g.name }}
                </span>
              </span>
            </div>

            <div>
              <strong>Recordings:</strong>
              <div v-if="!w.recordings?.length" class="text-muted">none</div>
              <ul v-else class="mb-0">
                <li v-for="r in w.recordings" :key="r.id">
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
      </tbody>
    </table>
  </div>
</template>
