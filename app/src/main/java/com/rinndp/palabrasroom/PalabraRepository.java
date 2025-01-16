package com.rinndp.palabrasroom;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PalabraRepository {
    private PalabraDAO mPalabraDAO;
    private LiveData<List<Palabra>> mPalabras;

    public PalabraRepository (Application application){
        PalabraDB db = PalabraDB.getDatabase(application);
        mPalabraDAO = db.palabraDAO();
        mPalabras = mPalabraDAO.getPalabrasOrdenadas();
    }

    LiveData<List<Palabra>> getAllPalabras() {
        return mPalabras;
    }

    void insert(Palabra palabra) {
        PalabraDB.databaseWriteExecutor.execute(() -> {
            mPalabraDAO.insert(palabra);
        });
    }

    void delete(Palabra palabra) {
        PalabraDB.databaseWriteExecutor.execute(() -> {
            mPalabraDAO.delete(palabra);
        });
    }
}
