const apiUrl = "http://localhost:8080/api";
const token = localStorage.getItem("userToken");

function getDefaultHeaders() {
    return {
        Authorization: `Bearer ${token}`,
        "Content-Type": "application/json",
    };
}

async function get(endpoint) {
    const url = `${apiUrl.replace(/\/$/, "")}/${endpoint.replace(/^\//, "")}`;

    try {
        const headers = new Headers();

        // Ne pas ajouter le JWT si l'endpoint est public
        if (endpoint !== "login") {
            const token = localStorage.getItem("userToken");
            if (token) {
                headers.append("Authorization", `Bearer ${token}`);
            }
        }

        const response = await fetch(url, {
            method: "GET",
            headers: headers,
            credentials: "include",
        });

        if (!response.ok) {
            throw new Error(`Erreur HTTP! Statut : ${response.status}`);
        }

        return await response.json();
    } catch (error) {
        console.error("Erreur lors de l'appel GET à l'API:", error.message);
        throw error;
    }
}


async function post(endpoint, data) {
    const url = `${apiUrl.replace(/\/$/, "")}/${endpoint.replace(/^\//, "")}`;

    try {
        const response = await fetch(url, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            credentials: "include",
            body: JSON.stringify(data),
        });

        if (!response.ok) {
            throw new Error(`Erreur HTTP! Statut : ${response.status}`);
        }
        return await response.json();
    } catch (error) {
        console.error("Erreur lors de l'appel POST à l'API:", error.message);
        throw error;
    }
}

async function put(endpoint, data) {
    const url = `${apiUrl.replace(/\/$/, "")}/${endpoint.replace(/^\//, "")}`;

    try {
        const response = await fetch(url, {
            method: "PUT",
            headers: getDefaultHeaders(),
            credentials: "include",
            body: JSON.stringify(data),
        });

        if (!response.ok) {
            throw new Error(`Erreur HTTP! Statut : ${response.status}`);
        }
        return await response.json();
    } catch (error) {
        console.error("Erreur lors de l'appel PUT à l'API:", error.message);
        throw error;
    }
}

async function del(endpoint) {
    const url = `${apiUrl.replace(/\/$/, "")}/${endpoint.replace(/^\//, "")}`;

    try {
        const response = await fetch(url, {
            method: "DELETE",
            headers: getDefaultHeaders(),
            credentials: "include",
        });

        if (!response.ok) {
            throw new Error(`Erreur HTTP! Statut : ${response.status}`);
        }

        // Vérifie si le contenu est présent ou si la réponse est vide
        return response.status === 204 ? {} : await response.json();
    } catch (error) {
        console.error("Erreur lors de l'appel DELETE à l'API:", error.message);
        throw error;
    }
}

async function postFile(endpoint, data) {
    const url = `${apiUrl.replace(/\/$/, "")}/${endpoint.replace(/^\//, "")}`;

    try {
        const response = await fetch(url, {
            method: "POST",
            body: data, // FormData ou fichier brut
            headers: {
                Authorization: `Bearer ${token}`,
            },
            credentials: "include",
        });

        if (!response.ok) {
            throw new Error(`Erreur HTTP! Statut : ${response.status}`);
        }
        return await response.json();
    } catch (error) {
        console.error("Erreur lors de l'appel POST FILE à l'API:", error.message);
        throw error;
    }
}

async function login(userData) {
    try {
        const response = await fetch(`${apiUrl.replace(/\/$/, "")}/login`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(userData),
        });

        if (response.status !== 200) {
            throw new Error("Erreur lors de la connexion");
        }

        const token = response.headers.get("access_token");
        if (!token) {
            throw new Error("Token manquant dans les headers de la réponse");
        }

        localStorage.setItem("userToken", token);

        return { success: true };
    } catch (error) {
        console.error("Erreur lors de la connexion :", error.message);
        throw error;
    }
}

export { get, post, put, del, postFile, login };