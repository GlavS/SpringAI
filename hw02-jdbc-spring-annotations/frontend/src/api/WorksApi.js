import axios from 'axios'

export const worksApi = {
    list() {
        return axios.get('/api/works').then(result => result.data);
    }
}