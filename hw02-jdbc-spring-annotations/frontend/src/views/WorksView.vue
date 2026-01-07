<script lang="ts" setup>
import {onMounted, ref} from 'vue'
import type {WorkDto} from "@/api/WorksApi"
import {worksApi} from "@/api/WorksApi"
import WorksRowView from "@/views/WorksRowView.vue";
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
      <WorksRowView
          v-for="w in state.data"
          :key="w.id"
          :expanded="expandedId === w.id"
          :work="w"
          @toggle="toggle"
      />
      </tbody>
    </table>
  </div>
</template>
