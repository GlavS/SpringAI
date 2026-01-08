<script lang="ts">
import { defineComponent } from 'vue'
import type { PropType } from 'vue'
import type { WorkDto } from '@/api/WorksApi'
import { worksApi } from '@/api/WorksApi'
import WorksRowView from '@/components/WorksRowView.vue'
// import { mockWorks } from "@/components/mocks/WorksViewMocks"

type LoadState =
    | { status: 'loading' }
    | { status: 'error'; message: string }
    | { status: 'success'; data: WorkDto[] }

export default defineComponent({
  name: 'WorksView',
  components: { WorksRowView },

  data() {
    return {
      state: { status: 'loading' } as LoadState,
      expandedId: null as number | null,
    }
  },

  methods: {
    toggle(id: number) {
      this.expandedId = this.expandedId === id ? null : id
    },

    getErrorMessage(e: unknown): string {
      return e instanceof Error ? e.message : String(e)
    },
  },

  async mounted() {
    this.state = { status: 'loading' }
    try {
      // const data = mockWorks // запрос из моков
      const data = await worksApi.list()
      this.state = { status: 'success', data }
    } catch (e: unknown) {
      this.state = { status: 'error', message: this.getErrorMessage(e) }
    }
  },
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
