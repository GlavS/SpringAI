<script lang="ts" setup>
import { onMounted, ref } from 'vue'
import {type GenreDto,
  type InstrumentDto,
  type ComposerDto,
  type RecordingDto,
  type WorkDto,
  worksApi} from "@/api/WorksApi";

const items = ref<WorkDto[]>([])
const error = ref<string>('')
const loading = ref<boolean>(false)
const expandedId = ref<number | null>(null)

const instrument: InstrumentDto = {
  id: 1,
  name: "Test instrument 1"
}

const composer: ComposerDto = {
  id: 1,
  name: "Name_1",
  surname: "Surname_1"
}

const genres: GenreDto[] = [
  {
    id: 1,
    name: "Classic"
  },
  {
    id: 2,
    name: "Baroque"
  }
]

const recordings: RecordingDto[] = [
  {
    id: 1,
    performer: "Test performer 1",
    label: "Test label 1",
    recordedAt: "20.03.1970",
    durationSec: 1800,
    sourceUrl: "http://test.com"
  },
  {
    id: 2,
    performer: "Test performer 2",
    label: "Test label 2",
    recordedAt: "21.03.1970",
    durationSec: 800,
    sourceUrl: "http://test.com"
  }
]

const mockValues: WorkDto[] = [
  {
    id: 1,
    title: "Test title 1",
    composer: composer,
    instrument: instrument,
    difficulty: "HARD",
    genres: genres,
    recordings: recordings
  },
  {
    id: 2,
    title: "Test title 2",
    composer: composer,
    instrument: instrument,
    difficulty: "EASY",
    genres: genres,
    recordings: recordings
  },
  {
    id: 3,
    title: "Test title 3",
    composer: composer,
    instrument: instrument,
    difficulty: "MEDIUM",
    genres: genres,
    recordings: recordings
  }
]

function toggle(id: number) {
  expandedId.value = expandedId.value === id ? null : id
}

function fmtDuration(sec: number) {
  if (sec == null) return ''
  const m = Math.floor(sec / 60)
  const s = sec % 60
  return `${m}:${String(s).padStart(2,'0')}`
}

// onMounted(async () => {
//   loading.value = true
//   error.value = ''
//   try {
//     items.value = await worksApi.list()
//   } catch (e) {
//     error.value = e instanceof Error?  e.message : String(e)
//   } finally {
//     loading.value = false
//   }
// })
onMounted(async () => {
  loading.value = true
  error.value = ''
  try {
    items.value = mockValues
  } catch (e) {
    error.value = e instanceof Error?  e.message : String(e)
  } finally {
    loading.value = false
  }
})
</script>

<template>
  <div class="container py-4">
    <h3 class="mb-3">Works</h3>

    <div v-if="error" class="alert alert-danger">{{ error }}</div>
    <div v-else-if="loading">Loading...</div>

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
      <template v-for="w in items" :key="w.id">
        <tr>
          <td>{{ w.id }}</td>
          <td>{{ w.title }}</td>
          <td>{{ w.composer?.name }} {{ w.composer?.surname }}</td>
          <td>{{ w.instrument?.name }}</td>
          <td>{{ w.difficulty }}</td>
          <td class="text-end">
            <button class="btn btn-sm btn-outline-primary" @click="toggle(w.id)">
              {{ expandedId === w.id ? 'Hide' : 'Details' }}
            </button>
          </td>
        </tr>

        <tr v-if="expandedId === w.id">
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
                  <span v-if="r.label"> — {{ r.label }}</span>
                  <span v-if="r.recordedAt"> ({{ r.recordedAt }})</span>
                  <span v-if="r.durationSec"> — {{ fmtDuration(r.durationSec) }}</span>
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
