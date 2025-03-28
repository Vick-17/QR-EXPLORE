import { useState, useEffect } from "react";
import "../Style/pages/PlaceList.css";
import { get } from "../service/BackendService";
import CardPlace from "../components/CardPlace";

const PlaceList = () => {
    const [searchTerm, setSearchTerm] = useState("");
    const [currentPage, setCurrentPage] = useState(0);
    const [places, setPlaces] = useState([]);
    const [totalPages, setTotalPages] = useState(0);
    const [isLoading, setIsLoading] = useState(true);

    const getPlaces = async (page = 0) => {
        try {
            setIsLoading(true);
            const response = await get(`/places?page=${page}&size=8`);
            setPlaces(response.content);
            setTotalPages(response.totalPages);
            setIsLoading(false);
        } catch (e) {
            console.error("Une erreur s'est produite lors de la récupération des lieux", e);
            setIsLoading(false);
        }
    };

    const searchPlaces = async (term) => {
        try {
            setIsLoading(true);
            const response = await get(`/places/search?name=${term}`);
            setPlaces(response);
            setTotalPages(1); // On suppose qu'il n'y a qu'une seule page pour les résultats de recherche
            setCurrentPage(0);
            setIsLoading(false);
        } catch (error) {
            console.error("Une erreur s'est produite lors de la recherche des lieux", error);
            setIsLoading(false);
        }
    };

    useEffect(() => {
        if (searchTerm.trim() === "") {
            getPlaces(currentPage);
        } else {
            searchPlaces(searchTerm);
        }
    }, [searchTerm, currentPage]);

    const handleSearch = (e) => {
        setSearchTerm(e.target.value);
    };

    const handlePageChange = (pageNumber) => {
        setCurrentPage(pageNumber);
    };

    return (
        <div className="container">
            <h1 className="title">Explore Places</h1>
            <div className="search-container">
                <input
                    type="text"
                    placeholder="Search for a place..."
                    value={searchTerm}
                    onChange={handleSearch}
                    className="search-bar"
                />
            </div>
            <div className="places-grid">
                {isLoading ? (
                    <p>Loading...</p>
                ) : (
                    places.map((place) => (
                        <CardPlace
                            key={place.id}
                            id={place.id}
                            name={place.name}
                            description={place.description}
                        />
                    ))
                )}
            </div>
            <div className="pagination">
                {[...Array(totalPages).keys()].map((page) => (
                    <button
                        key={page}
                        className={`pagination-item ${page === currentPage ? "active" : ""}`}
                        onClick={() => handlePageChange(page)}
                    >
                        {page + 1}
                    </button>
                ))}
            </div>
        </div>
    );
};

export default PlaceList;
