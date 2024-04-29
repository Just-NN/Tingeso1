import axios from 'axios';

const API_URL = 'http://localhost:3000/api/v1/ticket';

function getTicketById(id) {
    return axios.get(`${API_URL}/${id}`);
}

function saveTicket(ticket) {
    return axios.post(`${API_URL}/`, ticket);
}

function deleteTicket(id) {
    return axios.delete(`${API_URL}/${id}`);
}

function updateTicket(ticket) {
    return axios.put(`${API_URL}/`, ticket);
}

function getAllTickets() {
    return axios.get(`${API_URL}/`);
}

function saveBasePrice(ticket) {
    return axios.put(`${API_URL}/basePrice`, ticket);
}

function saveKMSurcharge(ticket) {
    return axios.put(`${API_URL}/kmSurcharge`, ticket);
}

function saveAgeSurcharge(ticket) {
    return axios.put(`${API_URL}/ageSurcharge`, ticket);
}

function saveSurchargeForDelay(ticket) {
    return axios.put(`${API_URL}/delaySurcharge`, ticket);
}

function saveDiscountByRepairs(ticket) {
    return axios.put(`${API_URL}/repairDiscount`, ticket);
}

function saveDiscountByDay(ticket) {
    return axios.put(`${API_URL}/dayDiscount`, ticket);
}

function saveBrandBonus(ticket) {
    return axios.put(`${API_URL}/brandBonus`, ticket);
}

function saveTotalPrice(ticket) {
    return axios.put(`${API_URL}/totalPrice`, ticket);
}

export default {
    getTicketById,
    saveTicket,
    deleteTicket,
    updateTicket,
    getAllTickets,
    saveBasePrice,
    saveKMSurcharge,
    saveAgeSurcharge,
    saveSurchargeForDelay,
    saveDiscountByRepairs,
    saveDiscountByDay,
    saveBrandBonus,
    saveTotalPrice
}