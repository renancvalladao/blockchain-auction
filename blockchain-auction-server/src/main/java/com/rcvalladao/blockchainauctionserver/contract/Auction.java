package com.rcvalladao.blockchainauctionserver.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.9.8.
 */
@SuppressWarnings("rawtypes")
public class Auction extends Contract {
    public static final String BINARY = "60806040523480156200001157600080fd5b5060405162000b4338038062000b438339810160408190526200003491620001d1565b8151829060009081906200004990826200032b565b50602082015160018201906200006090826200032b565b5060408201516002909101805460609093015160ff9081166101000261ffff19909416921691909117919091179055600380546001600160a01b03191633179055620000ad8142620003f7565b600655506200041f9050565b634e487b7160e01b600052604160045260246000fd5b604051608081016001600160401b0381118282101715620000f457620000f4620000b9565b60405290565b604051601f8201601f191681016001600160401b0381118282101715620001255762000125620000b9565b604052919050565b600082601f8301126200013f57600080fd5b81516001600160401b038111156200015b576200015b620000b9565b602062000171601f8301601f19168201620000fa565b82815285828487010111156200018657600080fd5b60005b83811015620001a657858101830151828201840152820162000189565b506000928101909101919091529392505050565b805160ff81168114620001cc57600080fd5b919050565b60008060408385031215620001e557600080fd5b82516001600160401b0380821115620001fd57600080fd5b90840190608082870312156200021257600080fd5b6200021c620000cf565b8251828111156200022c57600080fd5b6200023a888286016200012d565b8252506020830151828111156200025057600080fd5b6200025e888286016200012d565b6020830152506200027260408401620001ba565b60408201526200028560608401620001ba565b606082015260209590950151949694955050505050565b600181811c90821680620002b157607f821691505b602082108103620002d257634e487b7160e01b600052602260045260246000fd5b50919050565b601f8211156200032657600081815260208120601f850160051c81016020861015620003015750805b601f850160051c820191505b8181101562000322578281556001016200030d565b5050505b505050565b81516001600160401b03811115620003475762000347620000b9565b6200035f816200035884546200029c565b84620002d8565b602080601f8311600181146200039757600084156200037e5750858301515b600019600386901b1c1916600185901b17855562000322565b600085815260208120601f198616915b82811015620003c857888601518255948401946001909101908401620003a7565b5085821015620003e75787850151600019600388901b60f8161c191681555b5050505050600190811b01905550565b808201808211156200041957634e487b7160e01b600052601160045260246000fd5b92915050565b610714806200042f6000396000f3fe608060405234801561001057600080fd5b50600436106100575760003560e01c8063430ca46f1461005c5780637ae42ff9146100665780637e50bb83146100845780638e7ea5b2146100995780639979ef45146100c5575b600080fd5b6100646100d8565b005b61006e6101a2565b60405161007b91906105cb565b60405180910390f35b61008c610319565b60405161007b919061062b565b6100a161038e565b6040805182516001600160a01b03168152602092830151928101929092520161007b565b6100646100d336600461068b565b610414565b6003546001600160a01b031633146101455760405162461bcd60e51b815260206004820152602560248201527f4f6e6c7920746865206f776e65722063616e2066696e6973682074686520617560448201526431ba34b7b760d91b60648201526084015b60405180910390fd5b6006544210156101935760405162461bcd60e51b8152602060048201526019602482015278105d58dd1a5bdb881a185cc81b9bdd08195b991959081e595d603a1b604482015260640161013c565b6007805460ff19166001179055565b60408051608081018252606080825260208201819052600092820183905281019190915260006040518060800160405290816000820180546101e3906106a4565b80601f016020809104026020016040519081016040528092919081815260200182805461020f906106a4565b801561025c5780601f106102315761010080835404028352916020019161025c565b820191906000526020600020905b81548152906001019060200180831161023f57829003601f168201915b50505050508152602001600182018054610275906106a4565b80601f01602080910402602001604051908101604052809291908181526020018280546102a1906106a4565b80156102ee5780601f106102c3576101008083540402835291602001916102ee565b820191906000526020600020905b8154815290600101906020018083116102d157829003601f168201915b50505091835250506002919091015460ff808216602084015261010090910416604090910152919050565b60606008805480602002602001604051908101604052809291908181526020016000905b82821015610385576000848152602090819020604080518082019091526002850290910180546001600160a01b0316825260019081015482840152908352909201910161033d565b50505050905090565b604080518082019091526000808252602082015260075460ff166103f05760405162461bcd60e51b8152602060048201526019602482015278105d58dd1a5bdb881a185cc81b9bdd08195b991959081e595d603a1b604482015260640161013c565b50604080518082019091526004546001600160a01b03168152600554602082015290565b60075460ff16156104675760405162461bcd60e51b815260206004820152601960248201527f41756374696f6e2068617320616c726561647920656e64656400000000000000604482015260640161013c565b600081116104c35760405162461bcd60e51b815260206004820152602360248201527f4269642076616c7565206d7573742062652067726561746572207468616e207a60448201526265726f60e81b606482015260840161013c565b6005548110806104d35750600554155b156104f0576005819055600480546001600160a01b031916331790555b60408051808201909152338152602081019182526008805460018101825560009190915290517ff3f7a9fe364faab93b216da50a3214154f22a0a2b415b23a84c8169e8b636ee3600290920291820180546001600160a01b0319166001600160a01b0390921691909117905590517ff3f7a9fe364faab93b216da50a3214154f22a0a2b415b23a84c8169e8b636ee490910155565b6000815180845260005b818110156105ab5760208185018101518683018201520161058f565b506000602082860101526020601f19601f83011685010191505092915050565b6020815260008251608060208401526105e760a0840182610585565b90506020840151601f198483030160408501526106048282610585565b91505060ff604085015116606084015260ff60608501511660808401528091505092915050565b602080825282518282018190526000919060409081850190868401855b8281101561067e5761066e84835180516001600160a01b03168252602090810151910152565b9284019290850190600101610648565b5091979650505050505050565b60006020828403121561069d57600080fd5b5035919050565b600181811c908216806106b857607f821691505b6020821081036106d857634e487b7160e01b600052602260045260246000fd5b5091905056fea2646970667358221220592e461d866071a08c200dc41db045b0c898509b4e71b00c60a198f442af347f64736f6c63430008110033";

    public static final String FUNC_FINISHAUCTION = "finishAuction";

    public static final String FUNC_GETBIDHISTORY = "getBidHistory";

    public static final String FUNC_GETREQUIREMENTS = "getRequirements";

    public static final String FUNC_GETWINNER = "getWinner";

    public static final String FUNC_PLACEBID = "placeBid";

    @Deprecated
    protected Auction(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Auction(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Auction(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Auction(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> finishAuction() {
        final Function function = new Function(
                FUNC_FINISHAUCTION, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<List> getBidHistory() {
        final Function function = new Function(FUNC_GETBIDHISTORY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Bid>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<Requirements> getRequirements() {
        final Function function = new Function(FUNC_GETREQUIREMENTS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Requirements>() {}));
        return executeRemoteCallSingleValueReturn(function, Requirements.class);
    }

    public RemoteFunctionCall<WinnerInfo> getWinner() {
        final Function function = new Function(FUNC_GETWINNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<WinnerInfo>() {}));
        return executeRemoteCallSingleValueReturn(function, WinnerInfo.class);
    }

    public RemoteFunctionCall<TransactionReceipt> placeBid(BigInteger value) {
        final Function function = new Function(
                FUNC_PLACEBID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Auction load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Auction(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Auction load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Auction(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Auction load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Auction(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Auction load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Auction(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Auction> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, Requirements _requirements, BigInteger biddingTime) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(_requirements, 
                new org.web3j.abi.datatypes.generated.Uint256(biddingTime)));
        return deployRemoteCall(Auction.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Auction> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, Requirements _requirements, BigInteger biddingTime) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(_requirements, 
                new org.web3j.abi.datatypes.generated.Uint256(biddingTime)));
        return deployRemoteCall(Auction.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Auction> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, Requirements _requirements, BigInteger biddingTime) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(_requirements, 
                new org.web3j.abi.datatypes.generated.Uint256(biddingTime)));
        return deployRemoteCall(Auction.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Auction> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, Requirements _requirements, BigInteger biddingTime) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(_requirements, 
                new org.web3j.abi.datatypes.generated.Uint256(biddingTime)));
        return deployRemoteCall(Auction.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class Requirements extends DynamicStruct {
        public String vnfName;

        public String vnfType;

        public BigInteger numCpus;

        public BigInteger memSize;

        public Requirements(String vnfName, String vnfType, BigInteger numCpus, BigInteger memSize) {
            super(new org.web3j.abi.datatypes.Utf8String(vnfName), 
                    new org.web3j.abi.datatypes.Utf8String(vnfType), 
                    new org.web3j.abi.datatypes.generated.Uint8(numCpus), 
                    new org.web3j.abi.datatypes.generated.Uint8(memSize));
            this.vnfName = vnfName;
            this.vnfType = vnfType;
            this.numCpus = numCpus;
            this.memSize = memSize;
        }

        public Requirements(Utf8String vnfName, Utf8String vnfType, Uint8 numCpus, Uint8 memSize) {
            super(vnfName, vnfType, numCpus, memSize);
            this.vnfName = vnfName.getValue();
            this.vnfType = vnfType.getValue();
            this.numCpus = numCpus.getValue();
            this.memSize = memSize.getValue();
        }
    }

    public static class Bid extends StaticStruct {
        public String bidder;

        public BigInteger value;

        public Bid(String bidder, BigInteger value) {
            super(new org.web3j.abi.datatypes.Address(160, bidder), 
                    new org.web3j.abi.datatypes.generated.Uint256(value));
            this.bidder = bidder;
            this.value = value;
        }

        public Bid(Address bidder, Uint256 value) {
            super(bidder, value);
            this.bidder = bidder.getValue();
            this.value = value.getValue();
        }
    }

    public static class WinnerInfo extends StaticStruct {
        public String bidderAddress;

        public BigInteger cost;

        public WinnerInfo(String bidderAddress, BigInteger cost) {
            super(new org.web3j.abi.datatypes.Address(160, bidderAddress), 
                    new org.web3j.abi.datatypes.generated.Uint256(cost));
            this.bidderAddress = bidderAddress;
            this.cost = cost;
        }

        public WinnerInfo(Address bidderAddress, Uint256 cost) {
            super(bidderAddress, cost);
            this.bidderAddress = bidderAddress.getValue();
            this.cost = cost.getValue();
        }
    }
}
