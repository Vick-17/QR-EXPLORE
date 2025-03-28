import { post } from "../service/BackendService";

/* eslint-disable react/prop-types */
const CardPlace = ({ id, name, description }) => {


    const handleToLater = async () => {
        try {
            const response = await post(
                `/users/toLater`,
                [id]
            );
            console.log(response);
        } catch (e) {
            console.error("Error adding to later", e);
        }

    }

    const handleToFavorite = async () => {
        try {
            const response = await post(
                `/users/favorites`,
                [id]
            );
            console.log(response);
            
        } catch (e) {
            console.error("Error adding to later", e);
        }

    }


    return (
        <div className="place-card" key={id}>
            <div className="place-details">
                <h2 className="place-title">{name}</h2>
                <p className="place-description">{description}</p>
                <div className="place-actions">
                    <button onClick={handleToFavorite} className="btn_action">
                        Mettre en favories
                    </button>
                    <button onClick={handleToLater} className="btn_action">
                        A visiter plus tard
                    </button>
                </div>
            </div>
        </div>
    )
};

export default CardPlace;