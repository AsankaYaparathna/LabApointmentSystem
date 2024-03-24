var baseUrl = 'http://localhost:8080';

// Function to get all appointments
async function getAllAppointments() {
    try {
        var response = await fetch(baseUrl + '/LabAppointment-Service/resources/appointment', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        var data = await response.json();
        return data;
    } catch (error) {
        console.error('Error:', error);
        throw error;
    }
}

// Function to get appointment by ID
async function getAppointmentById(id) {
    try {
        var response = await fetch(baseUrl + `/LabAppointment-Service/resources/appointment/${id}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        if (response.ok) {
            var data = await response.json();
            return data;
        } else if (response.status === 404) {
            return null; // Appointment not found
        } else {
            throw new Error('Failed to get appointment by ID');
        }
    } catch (error) {
        console.error('Error:', error);
        throw error;
    }
}

// Function to get all doctors
async function getAllDoctors() {
    try {
        var response = await fetch(baseUrl + '/LabAppointment-Service/resources/doctor', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        var data = await response.json();
        return data;
    } catch (error) {
        console.error('Error:', error);
        throw error;
    }
}

// Function to get doctor by ID
async function getDoctorById(id) {
    try {
        var response = await fetch(baseUrl + `/LabAppointment-Service/resources/doctor/${id}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        if (response.ok) {
            var data = await response.json();
            return data;
        } else if (response.status === 404) {
            return null; // Doctor not found
        } else {
            throw new Error('Failed to get doctor by ID');
        }
    } catch (error) {
        console.error('Error:', error);
        throw error;
    }
}

// Function to get all patients
async function getAllPatients() {
    try {
        var response = await fetch(baseUrl + '/LabAppointment-Service/resources/patient', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        var data = await response.json();
        return data;
    } catch (error) {
        console.error('Error:', error);
        throw error;
    }
}

// Function to get patient by ID
async function getPatientById(id) {
    try {
        var response = await fetch(baseUrl + `/LabAppointment-Service/resources/patient/${id}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        if (response.ok) {
            var data = await response.json();
            return data;
        } else if (response.status === 404) {
            return null; // Patient not found
        } else {
            throw new Error('Failed to get patient by ID');
        }
    } catch (error) {
        console.error('Error:', error);
        throw error;
    }
}

// Function to get patient by ID
async function getPatientByUserName(id) {
    try {
        var response = await fetch(baseUrl + `/LabAppointment-Service/resources/patient/getbyusername/${id}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        if (response.ok) {
            var data = await response.json();
            return data;
        } else if (response.status === 404) {
            return null; // Patient not found
        } else {
            throw new Error('Failed to get patient by ID');
        }
    } catch (error) {
        console.error('Error:', error);
        throw error;
    }
}



// Function to get all tests
async function getAllTests() {
    try {
        var response = await fetch(baseUrl + '/LabAppointment-Service/resources/test', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        var data = await response.json();
        return data;
    } catch (error) {
        console.error('Error:', error);
        throw error;
    }
}

// Function to get reort by ID
async function getTestById(id) {
    try {
        var response = await fetch(baseUrl + `/LabAppointment-Service/resources/test/${id}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        if (response.ok) {
            var data = await response.json();
            return data;
        } else if (response.status === 404) {
            return null; // Test not found
        } else {
            throw new Error('Failed to get test by ID');
        }
    } catch (error) {
        console.error('Error:', error);
        throw error;
    }
}

async function getAllReport() {
    try {
        var response = await fetch(baseUrl + '/LabAppointment-Service/resources/report', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        var data = await response.json();
        return data;
    } catch (error) {
        console.error('Error:', error);
        throw error;
    }
}

// Function to get reort by ID
async function geReportById(id) {
    try {
        var response = await fetch(baseUrl + `/LabAppointment-Service/resources/report/${id}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        if (response.ok) {
            var data = await response.json();
            return data;
        } else if (response.status === 404) {
            return null; // Test not found
        } else {
            throw new Error('Failed to get test by ID');
        }
    } catch (error) {
        console.error('Error:', error);
        throw error;
    }
}

async function geReportByApointmentId(id) {
    try {
        var response = await fetch(baseUrl + `/LabAppointment-Service/resources/report/getbyapointment/${id}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        if (response.ok) {
            var data = await response.json();
            return data;
        } else if (response.status === 404) {
            return null; // Test not found
        } else {
            throw new Error('Failed to get test by ID');
        }
    } catch (error) {
        console.error('Error:', error);
        throw error;
    }
}