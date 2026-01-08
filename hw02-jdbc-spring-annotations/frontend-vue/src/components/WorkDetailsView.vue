<script setup lang="ts">

import {WorkDto, worksApi} from "@/api/WorksApi";
import {useRoute, useRouter} from "vue-router";
import {computed, onMounted, ref} from "vue";
import WorkDetailsPanel from "@/components/WorkDetailsPanel.vue";
import {mockWorks} from "@/components/mocks/WorksViewMocks";

type LoadState =
    | { status: 'loading' }
    | { status: 'success', data: WorkDto }
    | { status: 'error', message: string }

const route = useRoute()
const router = useRouter()
const state = ref<LoadState>({status: 'loading'})
const id = computed(() => {
  const rawValue = route.params.id
  return typeof rawValue === "string" ? Number(rawValue) : Array.isArray(rawValue) ? Number(rawValue[0]) : Number(rawValue)
})

function getErrorMessage(e: unknown): string {
  return e instanceof Error ? e.message : String(e)
}

async function load() {
  state.value = {status: "loading"}
  try {
    if (!Number.isFinite(id.value)) {
      state.value = {status: "error", message: `Invalid id: ${String(route.params.id)}`}
      return
    }
    const data = await worksApi.getById(id.value)
    // const tempId = id.value - 1
    // const data = mockWorks[tempId]
    state.value = {status: "success", data: data}
  } catch (e) {
    state.value = {status: "error", message: getErrorMessage(e)}
  }
}

function goBackToList() {
  router.push("/works")
}

onMounted(load)

</script>

<template>
  <div class="container py-4">
    <div class="d-flex align-items-center justify-content-between mb-3">
      <div>
        <h3 class="mb-0">Work details</h3>
        <div class="text-muted" v-if="state.status === 'success'">#{{ state.data.id }}</div>
      </div>

      <div class="d-flex gap-2">
        <button class="btn btn-outline-secondary" @click="goBackToList">Back</button>
        <!-- На будущее: Edit/Delete -->
        <!--
        <button v-if="state.status === 'success'" class="btn btn-primary" @click="router.push(`/works/${state.data.id}/edit`)">
          Edit
        </button>
        -->
      </div>
    </div>

    <div v-if="state.status === 'error'" class="alert alert-danger">
      {{ state.message }}
    </div>

    <div v-else-if="state.status === 'loading'">Loading...</div>

    <div v-else>
      <div class="card mb-3">
        <div class="card-body">
          <div class="row g-2">
            <div class="col-md-6">
              <strong>Title:</strong>
              <span class="ms-1">{{ state.data.title }}</span>
            </div>
            <div class="col-md-6">
              <strong>Difficulty:</strong>
              <span class="ms-1">{{ state.data.difficulty }}</span>
            </div>
            <div class="col-md-6">
              <strong>Composer:</strong>
              <span class="ms-1">{{ state.data.composer?.name }} {{ state.data.composer?.surname }}</span>
            </div>
            <div class="col-md-6">
              <strong>Instrument:</strong>
              <span class="ms-1">{{ state.data.instrument?.name }}</span>
            </div>
          </div>
        </div>
      </div>

      <div class="card">
        <div class="card-body">
          <!-- WorkDetailsPanel сейчас рендерит <td colspan="6">, поэтому тут оборачиваем в таблицу -->
          <table class="table mb-0">
            <tbody>
            <tr>
              <WorkDetailsPanel :work="state.data" />
            </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>
