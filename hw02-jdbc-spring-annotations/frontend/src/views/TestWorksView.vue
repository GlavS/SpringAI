<script lang="ts" setup>
import {onMounted, ref} from 'vue'
import {type WorkDtoSmall, worksApi} from "@/api/WorksApi";

const items = ref<WorkDtoSmall[]>([])
const error = ref<string>('')
const loading = ref<boolean>(false)

const mockValues: WorkDtoSmall[] =
    [
      {
        "id": 1,
        "title": "Test title 1"
      },
      {
        "id": 2,
        "title": "Test title 2"
      },
      {
        "id": 3,
        "title": "Test title 3"
      },
      {
        "id": 4,
        "title": "Test title 4"
      },
      {
        "id": 5,
        "title": "Test title 5"
      },
    ]


// onMounted(async () => {
//   loading.value = true;
//   error.value = '';
//   try {
//     items.value = await worksApi.list();
//   } catch (e: unknown) {
//     error.value = e instanceof Error ? e.message : String(e)
//   } finally {
//     loading.value = false
//   }
// })
onMounted(async () => {
  loading.value = true;
  error.value = '';
  try {
    items.value = mockValues;
  } catch (e: unknown) {
    error.value = e instanceof Error ? e.message : String(e)
  } finally {
    loading.value = false
  }
})
</script>


<template>
  <div class="container py-4">
    <h3 class="mb-3">Works</h3>

    <div v-if="error.length" class="alert alert-danger">{{ error }}</div>
    <div v-else-if="loading">Loading...</div>

    <table v-else class="table table-striped">
      <thead>
      <tr>
        <th style="width: 80px;">ID</th>
        <th>Title</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="w in items" :key="w.id">
        <td>{{ w.id }}</td>
        <td>{{ w.title }}</td>
      </tr>
      </tbody>
    </table>
  </div>

</template>
