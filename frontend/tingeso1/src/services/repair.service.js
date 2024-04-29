import axios from 'axios';

const API_URL = 'http://localhost:8090/api/v1/repair';

function getRepairById(id) {
    return axios.get(`${API_URL}/${id}`);
}

function saveRepair(repair) {
    return axios.post(`${API_URL}/`, repair);
}

function deleteRepair(id) {
    return axios.delete(`${API_URL}/${id}`);
}

function updateRepair(repair) {
    return axios.put(`${API_URL}/`, repair);
}

function getAllRepairs() {
    return axios.get(`${API_URL}/`);
}

export default {
    getRepairById,
    saveRepair,
    deleteRepair,
    updateRepair,
    getAllRepairs
}