<script lang="ts" setup>
import type {WorkDto} from "@/api/WorksApi";
import WorkDetailsPanel from "@/views/WorkDetailsPanel.vue";

defineProps<{
  work: WorkDto
  expanded: boolean
}>()

const emit = defineEmits<{
  (e: 'toggle', id: number): void
}>()

</script>

<template>
  <tr style="cursor: pointer" @click="emit('toggle', work.id)">
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
    <WorkDetailsPanel :work="work"/>
  </tr>
</template>

